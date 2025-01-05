**GET STARTED**

> [!WARNING]
> This instruction is not for newbies,
> the instruction is for people, who understand
> the basic programming concepts 🐊

Here you go! Firstly, thanks you of interesting the Polar language.
Let's start our adventure! 🧶

**INTRO** 🗞️

The Polar is dynamic typed, object-oriented language.

**TYPES & VARS** 🕵️

All variables is dividing to scopes. Current thread func call scope,
global scope, class instance scope.

> [!IMPORTANT]
> The comments writing in two sharps, like this:
> ```polar
> # the comment example #
> ```

The variables is declaring like this:
```polar
a = 5
b = 'text'
c = true
# etc #
```

The language contains this types:
1. Number (float under the hood), declaring like in 
example 🔢: 
```polar
a = 7
b = 1.253
c = 9.736
# etc #
 ```
2. Text (string under the hood), declaring in single quotes 💬:
```polar
a = '12345'
b = ''
# etc #
```
3. Bool (boolean under the hood), declaring like this 🤖:
```polar
a = true
b = false
# etc #
 ```
4. PolarObject (instance of PolarClass under the hood), declaring with new keyword 🐩: 
```polar
a = new SomeClass()
```
4. Reflected (java object under the hood), declaring like this:
```polar
a = reflect 'some_awesome_java_class_name'
```
Needs for java reflection such as a method call, class creation, etc...
5. Func (FunctionStatement under the hood)
```polar
a = some_avesome_exists_function
```

> [!WARNING]
> The documentation is under development