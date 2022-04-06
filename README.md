# TestWorkIronWaterStudio

Тестовое решение

Нужно создать приложение для вывода информации о продуктах.

1.       Среда разработки - Android Studio, язык - Kotlin.

2.       Приложение должно работать на устройствах с Android 5.0 и выше, c поддержкой поворотов экрана. На всех экранах приложения должен быть стандартный App Bar.

3.       Продукт содержит следующие поля: id, название, изображение, описание. Информацию о продуктах необходимо хранить в JSON файле, изображения - в ресурсах приложения (в JSON файле будут хранится названия ресурсов).

4.       В главном экране приложения необходимо  загрузить список продуктов из файла и отобразить их на экране в виде списка, используя RecyclerView. В каждом элементе списка должно быть 2 текстовых поля, расположенных вертикально и содержащих id и название продукта соответственно. При нажатии на продукт из списка мы должны попасть на экран с описанием продукта. В App Bar нужно добавить кнопку "О компании", по которой нужно открыть экран "О компании".

5.       На экране описания продукта нужно последовательно отобразить всю информацию о продукте, а в App Bar включить стандартную кнопку "Up", по нажатию на которую мы вернемся на предыдущий экран.

На экране "О компании" нужно отобразить логотип, название и ссылку на сайт компании, по нажатию на которую покажется диалог с текстом "Открыть сайт в браузере?" и кнопками "ОК", "Отмена". По нажатию на "ОК" диалог закроется, а в браузере по умолчанию откроется сайт компании. Кнопка "Отмена" просто закрывает диалог. В App Bar также должна быть кнопка "Up", по нажатию на которую мы вернемся на предыдущий экран.

Результат работы представлен ниже:

<img src = "./screen/Record_2022-04-07-01-57-49.gif">.

 
 

