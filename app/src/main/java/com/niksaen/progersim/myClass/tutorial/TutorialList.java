package com.niksaen.progersim.myClass.tutorial;

import java.util.HashMap;

public class TutorialList {
    public HashMap<String,String[]> list = new HashMap<>();
    public TutorialList(){
        list.put("RU",RU);
        list.put("EN",EN);
    }


    private static final String[] RU = {
            "Характеристики игрока:\nПервая характеристика это деньги, в начале игры вам дают 18000. Деньги - игровая валюта, используемая для покупки различных товаров: книги, еда, комплектующие ПК, программы\n",
            "Далее идет энергия, в начале игры равняется 80, максимум 100. Энергия используется для работы и кодинга, для пополнения перейдите в магазин еды",
            "Следом идёт опыт, при старте равен 0. Опыт нужен для поднятия вашего уровня, пополняется при программировании",
            "И последняя характеристика это ваш уровень, в начале ваш уровень \"Junior 1\". Уровень нужен для того чтобы вы больше зарабатывали денег и опыта, также он нужен для покупки некоторых книг",
            "В магазине вы можете покупать разные товары, такие как: книги, программы, комплектующие ПК, еду. Для пререхода нажмите на кнопку \"Магазин\"",
            "Выбор работы:\n С самого начала игры вам доступно три вида работ:\n1. Доставка пиццы\n2. Работа курьером\n3. Раздача листовок.\n По мере прокачки будут открываться новые работы",
            "Пополнение энергии:\n Рассмотрим пополнение энергии более детально: \n Первое это то, что вам необходимо перейти в \"Магазин\", затем выберите раздел  \"Продукты\". После чего выберите один из продуктов и пополните запас нажав на кнопку  \"Купить\". Помните вы не можете пополнить энергию больше 100.",
            "Сборка ПК:\nСборка это довольно сложный процесс, так что если не хотите возиться можете купить готовый пк в разделе \"Покупка ПК\". Для покупки комплектующих перейдите в раздел  \"Комплектующие ПК\"",
            "Ну чтож, приступим к сборке.  Первое без чего не может быть ни один ПК это корпус, его можно выбрать исходя из вкусовых предпочтений, но он обязятелен.",
            "Далее нам необходимо выбрать материнскую плату, одним из основных параметром при её выборе это сокет. Ведь именно он определяет какой процессор подойдёт, а какой нет. Далее на что стоит обратить внимание это количество слотов для оперативки, тут простое правило: чем больше тем лучше. В начале игры хватит и двух слотов. Потом обратите внимание на количество портов SATA они определяют, то сколько накопителей вы сможете подключить. ",
            "После выбора материнки необходимо выбрать процессор - это мозг ПК, Для того чтобы процессор подошёл у него должен быть такой же сокет, как у материнской платы. Обратите внимание на количество ядер и потоков, их должно быть побольше. Следующим параметром является частота,  тут тоже простое правило: чем больше тем лучше. Но учтите что брать очень дорогой процессор на старте смысла нет.",
            "Теперь выберем охлаждение процессора, для того чтобы охлаждение подошло необходимо чтобы: тепловыделение процессора было меньше ли равно рассеиваемой мощности кулера",
            "Теперь можно выбрать оперативную память, для того чтобы оперативка подошла к нашему ПК. Её частота должна входить в поддерживаемый диапозон частот.",
            "Как определить диапозон поддерживаемых частот, для этого у материнки и проца есть параметры  \"Минимальная частота\" и \"Максимальная частота\". Что делать если они не совпадают? Если не совпадает параметр  \"Минимальная частота\", то минимальная поддерживаемая частота определяется наибольшим из этих параметров",
            "Если же не совпадают параметры \"Максимальная частота\", то верхний порог для частоты определяется наименьшим параметром",
            "Выберем видеокарту, тут всё просто если для программы нужен хороший графический процессор (наиболее актуально для игровых движков) или у процессора отсутствует встроенное графическое ядро, то придётся брать, в противном случае лучше съэкономить.",
            "Далее выберем накопитель, итак если нужна большая скорость установки программ, берите SSD. Если вам нужен большой объём, то возьмите HDD.",
            "Блок питания - тут всё очень просто если у вас нет внешней(дискретной) видеокарты, то берите любой, в противном случае выбирайте блок питания, той мощности, которая указана в характеристиках видеокарты.",
            "После того как комплектующие куплены и подобраны, необходимо их установить. Для это нажмите на человека сидящего за компьютером, после чего выберите раздел \"Сборка ПК\". ",
                    "Порядок сборки\n" +
                    "1. Установите корпус\n" +
                    "2. Установите материнскую плату \n" +
                    "3. Установите процессор\n" +
                    "4. Установите кулер\n" +
                    "5. Установите оперативную память.\n" +
                    "6. Установите видеокарту, если куплена\n" +
                    "7. Установите накопитель\n" +
                    "8. Установите блок питания\n" +
                    "Примечание: Для таких запчастей как опертивка и накопители, иногда и для видеокарт,  необходимо выбрать слот в который вы хотите установить деталь.",
            "Выбор и установка программы:\n Перед покупкой программы сверьте системные требование с характеристиками своего ПК, они должны совпадать.Покупка программ осуществляется в разделе \"Программы\".",
            "Для перехода к установке программы нажмите на человека сидящего за компьютером, после чего выберите раздел \"Взаимодействие с ПК\".  Затем нажмите на раздел \"Установка программ\", после чего вам предложат выбрать диск для установки, далее выберите программу, потом поставьте галочку на против \"Я принимаю\", дальше нажмите на кнопку \"Установить\", и дождитесь окончания установки. Примечание: Время установки зависит от типа накопителя который вы выбрали.",
            "Покупка и изучение книг:\n Перед покупкой книги убедитесь в том что у вас подходящий уровень, после покупки вы можете приступить к её изучению. Для этого нажмите на шкаф, и затем коснитесь надписи \"Изучить\", и если вы всё правильно сделали, то вы сможете зарабатывать деньги и опыт.",
            "Совместимость програм и книг можно просмотреть в разделе \"Помощь\", который находиться в правом верхнем углу"
    };

    private static final String[] EN = {
            "Player characteristics: \nThe first characteristic is money, at the beginning of the game you are given 18000. Money is the game currency used to buy various goods: books, food, PC components, programs \n",
            "Next comes energy, at the beginning of the game it is 80, maximum 100. Energy is used for work and coding, go to the food store to refill",
            "Next comes experience, at the start it is equal to 0. Experience is needed to raise your level, it is replenished during programming",
            "And the last characteristic is your level, at the beginning your level is \" Junior 1 \". The level is needed in order for you to earn more money and experience, it is also needed to buy some books,",
            "In the store you can buy various goods, such as: books, programs, PC components, food. To go through, click on the \" Store \"button",
            "Choice of job: \n From the very beginning of the game, you have three types of jobs: \n1. Pizza delivery \n2. Courier work \n3. Distribution of leaflets. \n As you level up, new jobs will open up",
            "Energy replenishment: \n Let's consider energy replenishment in more detail: \n The first is that you need to go to \" Store \", then select the \" Products \"section. Then select one of the products and replenish the stock by clicking on the button \"Buy \". Remember, you cannot replenish more than 100 energy. ",
            "Assembling a PC: \nAssembling is a rather complicated process, so if you don't want to mess around, you can buy a ready-made PC in the \" Purchase a PC \"section. To purchase components, go to \" PC components \"",
            "Well, let's get down to assembling. The first thing no PC can be without is a case, you can choose it based on your taste preferences, but it's a must.",
            "Next, we need to choose a motherboard, one of the main parameters when choosing it is a socket. After all, it is he who determines which processor is suitable and which is not. Next, what you should pay attention to is the number of slots for RAM, there is a simple rule: the more the better At the beginning of the game, two slots are enough. Then pay attention to the number of SATA ports, they determine how many drives you can connect. ",
            "After choosing a motherboard, you need to choose a processor - this is the brain of the PC, In order for the processor to fit, it must have the same socket as the motherboard. Pay attention to the number of cores and threads, there must be more of them. The next parameter is the frequency, here too a simple rule: the more the better. But keep in mind that it makes no sense to take a very expensive processor at the start. ",
            "Now we will select the processor cooling, in order for the cooling to fit, it is necessary that: the heat dissipation of the processor is less than the dissipated power of the cooler",
            "Now you can select the RAM in order for the RAM to come to our PC. Its frequency must be included in the supported frequency range.",
            "How to determine the range of supported frequencies, for this the motherboard and the processor have parameters \" Minimum frequency \"and \" Maximum frequency \". What to do if they do not match? the frequency is determined by the largest of these parameters ",
            "If the parameters \" Maximum frequency \"do not match, then the upper threshold for the frequency is determined by the smallest parameter",
            "Let's choose a video card, everything is simple here, if the program needs a good graphics processor (most important for game engines) or the processor does not have a built-in graphics core, then you will have to take it, otherwise it is better to save money.",
            "Next, we will choose a drive, so if you need a high speed of installation of programs, take an SSD. If you need a large volume, then take an HDD.",
            "Power supply - everything is very simple here, if you do not have an external (discrete) video card, then take any, otherwise choose a power supply of the power that is indicated in the characteristics of the video card",
            "After the components are purchased and selected, you need to install them. To do this, click on the person sitting at the computer, then select the \" PC assembly \"section.",
            "Build order \n" +
                    "1. Install the case \n" +
                    "2. Install the motherboard \n" +
                    "3. Install the processor \n" +
                    "4. Install the cooler \n" +
                    "5. Install RAM. \n" +
                    "6. Install the video card if purchased \n" +
                    "7. Install the drive \n" +
                    "8. Install the power supply \n" +
                    "Note: For such spare parts as operating systems and drives, sometimes for video cards, you must select the slot in which you want to install the part.",
            "Selecting and installing the program: \n Before purchasing the program, check the system requirements with the characteristics of your PC, they must match. The purchase of programs is carried out in the \" Programs \"section.",
            "To proceed to the installation of the program, click on the person sitting at the computer, then select the \" Interaction with PC \"section. Then click on the \" Install programs \"section, then you will be prompted to select the disk to install, then select the program, then put check the box against \"I accept \", then click on the \"Install \" button, and wait until the installation is complete. Note: The installation time depends on the type of drive you have chosen. ",
            "Buying and studying books: \n Before buying a book, make sure that you have a suitable level, after purchasing you can start studying it. To do this, click on the cabinet, and then touch the inscription \" Study \", and if you are correct done, then you can earn money and experience. ",
            "The compatibility of programs and books can be viewed in the \" Help \"section, which is located in the upper right corner"
    };
}
