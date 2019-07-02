/***********************************************************************************
* driver for leds
*
**********************************************************************************/
#include <linux/miscdevice.h>
#include <linux/input.h>
#include <linux/clk.h>
#include <linux/delay.h>
#include <asm/io.h>
#include <asm/uaccess.h>
#include <linux/module.h>
#include <linux/init.h>
#include <linux/of_gpio.h>
#include <linux/gpio.h>
#include <linux/of_platform.h>
#include <linux/device.h>



static int major;
static struct class *cls;
static int rednum,greennum;
static int led_open(struct inode *inode, struct file *file)
{
	printk("first_drv_open\n");
	/* 配置为输出 */

	return 0;	
}

static ssize_t led_write(struct file *file, const char __user *buf, size_t count, loff_t * ppos)
{
	return 0;
}
/* app : ioctl(fd, cmd, arg) */
static long led_ioctl(struct file *filp, unsigned int cmd,unsigned long arg)
{
	/* 根据传入的参数设置GPIO */
	/* cmd : 0-off, 1-on */
	/* arg : 0-1, which led */

	if ((cmd != 0) && (cmd != 1))
		return -EINVAL;
	
	if (arg > 2)
		return -EINVAL;
		
	if(cmd==1)
	{
		if(arg==0)
			gpio_set_value(rednum, cmd);
		if(arg==1)
			gpio_set_value(greennum, cmd);
	}
	else
	{
		if(arg==0)
			gpio_set_value(rednum, cmd);
		if(arg==1)
			gpio_set_value(greennum, cmd);
	}
		
	return 0;
}


static struct file_operations led_fops = {
    .owner  =   THIS_MODULE,    /* 这是一个宏，推向编译模块时自动创建的__this_module变量 */
    .open   =   led_open,     
	.write	=	led_write,	
	.unlocked_ioctl	= led_ioctl,
};

static int leds_probe(struct platform_device *pdev)
{  
	struct device_node *led_node = pdev->dev.of_node;
	enum of_gpio_flags flags;
	printk("leds+probe..............\n");

	/* 注册字符设备驱动程序 */

	major = register_chrdev(0, "myled", &led_fops);

	cls = class_create(THIS_MODULE, "myled");

	device_create(cls, NULL, MKDEV(major, 0), NULL, "led"); /* /dev/led */

	//get ctl1 gpio
	rednum = of_get_named_gpio_flags(led_node, "ledRed-pwr", 0, &flags);
	if(!gpio_is_valid(rednum))
	{
		printk(KERN_ERR "red_pwr_pin: invalid gpio %d\n", rednum);
		return -EINVAL;
	}
	if(devm_gpio_request(&pdev->dev, rednum, "red_pwr_pin") != 0)
	{
		printk("ledDatapwr-pin: can not request gpio %d\n", rednum);
		return -EINVAL;
	}else{
		gpio_direction_output(rednum, 1);
	}
	
	greennum = of_get_named_gpio_flags(led_node, "ledGreenBlue-pwr", 0, &flags);
	if(!gpio_is_valid(greennum)){
		printk(KERN_ERR "greenBlue_pwr_pin: invalid gpio %d\n", greennum);
		return -EINVAL;
	}
	if(devm_gpio_request(&pdev->dev, greennum, "greenBlue_pwr_pin") != 0){
		printk("ledDatapwr-pin: can not request gpio %d\n", greennum);
		return -EINVAL;
	}else{
		gpio_direction_output(greennum, 1);
	}

	return 0;
}
static int leds_remove(struct platform_device *pdev)
{
	printk("led_remove, remove led\n");

	device_destroy(cls, MKDEV(major, 0));
	class_destroy(cls);
	unregister_chrdev(major, "myled");

    return 0;
}
static struct of_device_id leds_of_match[] = {
        { .compatible = "hisense,led" },
        { }
};

static struct platform_driver leds_driver = {
        .driver         = {
                .name           = "hisense,led",            
                .of_match_table = of_match_ptr(leds_of_match),
        },
        .probe          = leds_probe,
        .remove         = leds_remove,
};
MODULE_DEVICE_TABLE(of, leds_of_match);

static int  leds_init(void)
{
    printk(KERN_INFO "Enter %s\n", __FUNCTION__);
    return platform_driver_register(&leds_driver);
}
static void  leds_exit(void)
{
	platform_driver_unregister(&leds_driver);
    printk("close leds\n");
}
module_init(leds_init);
module_exit(leds_exit);
//module_platform_driver(leds_driver);
MODULE_DESCRIPTION("leds Driver");
MODULE_LICENSE("GPL");
MODULE_ALIAS("platform:leds-drivers");

