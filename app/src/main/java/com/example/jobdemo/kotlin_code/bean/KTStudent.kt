package com.example.jobdemo.kotlin_code.bean

/*
* 继承一个类用“:”比号就可以了，
* 在类名后面加括号代表定义主构造函数,括号里可以直接设置要接收的参数，等于Java定义了一个构造方法
* init相当于主构造函数体，每次创建对象都会调用
* 次构造函数要用constructor修饰，而且次构造函数一定要调用主构造函数，如果类不定义主构造函数（就是类后面不加括号，
* 那父类后面也可以不加括号如：class KTStudent : KTPerson，但是类里面的次级函数还是要调用主构造函数，只是类没有定义，
* 只能调用父类的，用super关键字
*
* 主构造函数中name 和age 没有用var修饰，因为用var修饰的字段会默认编译为本类字段，这样和父类的字段就冲突了
*
* KT中继承父类和实现接口都是用冒号“：”，一个冒号就够了，用逗号分隔
* */
class KTStudent(var sno: String, var grade: Int, name: String, age: Int) : KTPerson(), Study {
    init {
        println("init调用了==" + this.name)
    }


    //这里定义了一个次构造函数，且给son和grade设置了默认值（记得要调用主构造函数）
    constructor(name: String, age: Int) : this("默认学号", 496, name, age) {
        println("调用了次级两个参数的构造函数" + toString())
    }

    /* 不需要参数的次级构造函数，调用了两个参数的次级构造函数，两个参数的次级构造函数调用了主构造函数，
    这样也是没问题的
    */
    constructor() : this("默认名字", 596) {
        //会先打印init里的内容，在打印两个参数构造函数里的内容，相当于继承且调用了父类的方法
        println("调用了次级空构造函数" + toString())
    }

    override fun readBooks() {
        println("实现了readBooks")
    }

    override fun toString(): String {
        return "KTStudent(sno='$sno', grade=$grade)"
    }
}