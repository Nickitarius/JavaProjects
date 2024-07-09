-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Мар 30 2022 г., 13:49
-- Версия сервера: 10.4.18-MariaDB
-- Версия PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `delo`
--

-- --------------------------------------------------------

--
-- Структура таблицы `tag`
--

CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `tag`
--

INSERT INTO `tag` (`id`, `name`) VALUES
(1, 'Urgent'),
(2, 'Work'),
(3, 'Home');

-- --------------------------------------------------------

--
-- Структура таблицы `tag_zadacha`
--

CREATE TABLE `tag_zadacha` (
  `Tag_id` int(11) NOT NULL,
  `zadachas_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `zadacha`
--

CREATE TABLE `zadacha` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `opisanie` varchar(255) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `zadacha`
--

INSERT INTO `zadacha` (`id`, `date`, `opisanie`, `tag_id`) VALUES
(1, '0001-03-31', '3131', 2),
(2, '4224-02-04', '4242', 2),
(3, '4224-02-04', '4242', 2),
(4, '0666-06-06', '666', 3),
(5, '1213-12-22', '3131', 2),
(6, '0252-05-05', '52', 2),
(7, '0006-06-06', '6', 3),
(8, '0063-03-31', '2622', 3),
(9, '0007-02-20', '2027', 1),
(10, '0053-03-05', '53', 2),
(11, '0053-03-05', 'ffff', 1),
(12, '0053-04-04', 'dada', 2),
(13, '2022-03-30', 'today', 1),
(14, '2022-03-28', 'n', 1),
(15, '2022-03-01', '212', 1),
(16, '2022-09-09', 'september', 3);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `tag_zadacha`
--
ALTER TABLE `tag_zadacha`
  ADD PRIMARY KEY (`Tag_id`,`zadachas_id`),
  ADD UNIQUE KEY `UK_82hpabkp6xejb8cto94xvwi1t` (`zadachas_id`);

--
-- Индексы таблицы `zadacha`
--
ALTER TABLE `zadacha`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcduvci872cuu89e8m333lo338` (`tag_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `zadacha`
--
ALTER TABLE `zadacha`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
