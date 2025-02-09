<p align="center">
  <img width="200" height="190" src="https://github.com/user-attachments/assets/125d62df-afae-4db7-8109-12ee55bda571">
</p>

<p align="center">
  Polar programming language
</p>



Dynamicly typed simple scripting
programming language 💡

Compiles to IceVM 🥶

> [!IMPORTANT]
> Compiler & Vm still under dev. It doesn't
> support the old libs (from interpreter)

> [!NOTE]
> Requires kotlin 5.0 & jdk 21

Example code:
```python
use 'lib.random'

random = new Random()
rnd_value = random.number(1, 100)

while (1 == 1) {
  put('Guess the number between 1 and 100 🛸 !')
  input = scan('Enter number...')
  if (num(input) == rnd_value) {
    put('Guessed! 💡')
  } else {
    put('Wrong number! The answer was... ' + string(rnd_value) + '! 🚨')
  }
  rnd_value = random.number(1, 100)
}
```

> [!TIP]
> Read documentation for more examples

> [!WARNING]
> Documentation is work in progress
