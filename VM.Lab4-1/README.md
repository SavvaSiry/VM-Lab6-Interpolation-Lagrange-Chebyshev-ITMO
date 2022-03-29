# VM.Lab4

[![GitHub stars][stars-shield]][stars-url]
[![GitHub issues][issues-shield]][issues-url]
[![GitHub][license-shield]][license-url]
![GitHub repo size](https://img.shields.io/github/repo-size/KirillShakhov/VM.Lab4)
![GitHub last commit](https://img.shields.io/github/last-commit/KirillShakhov/VM.Lab4)

Лабораторная работа 4. «Интерполирование Лагранжа»
-------------------------------------------------

Вычислительная математика ЛР4. Вариант: Интерполирование Лагранжа (ИТМО, ПИиКТ, 2 курс)

Вариант: Метод Лагранжа

Для интерполяции необходимо подготовить 3-4 набора данных (в зависимости от функции).
Исходные данные должны быть подготовлены следующим образом: 
* Берем функцию
* Берем точки x (точки не обязательно упорядочены)
* значение y получаем на основе данных выбранной функции

Например:
* берем sinx
* 1) берем 3-4 точки на интервале 0 по 2Пи(шаг более менее большой)
  2) берем 8-10 точек на интервале 0 по 2Пи (уменьшаем шаг)
  3) точки с предыдущего примера, только для одной точки изменяем значение y, например
было 0.8, делаем -5, смотрим как ведет себя интерполяция.
  4) берем 8-10 точек на интервале 0 по 50Пи

В итоге, должны получить график, на котором одним цветом исходная функция (sinx), а другим цветом полученный график в результате интерполяции, и на графике должны быть отмечены сами точки (узлы) интерполяции. 
Интерполяционный график должен пройти через исходные эти точки.
Программа должна позволять найти значение y (отдельное поле) для любого введенного x
(рассчитывается на основе построенного интерполяционного многочлена).

TAGS: Интерполирование методом Лагранжа на Java. Интерполирование и линейное аппроксимирование на Java. Метод Лагранжа и построение графиков. Нахождение точки по исходным данным.

[stars-shield]: https://img.shields.io/github/stars/KirillShakhov/VM.Lab4?style=social
[stars-url]: https://github.com/KirillShakhov/VM.Lab4/stargazers
[issues-shield]: https://img.shields.io/github/issues/KirillShakhov/VM.Lab4
[issues-url]: https://github.com/KirillShakhov/VM.Lab4/issues
[license-shield]: https://img.shields.io/github/license/KirillShakhov/VM.Lab4
[license-url]: https://github.com/KirillShakhov/VM.Lab4/blob/master/LICENSE
