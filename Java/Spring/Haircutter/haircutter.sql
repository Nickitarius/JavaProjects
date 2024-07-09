-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 18 2021 г., 06:10
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
-- База данных: `haircutter`
--

-- --------------------------------------------------------

--
-- Структура таблицы `haircut_case`
--

CREATE TABLE `haircut_case` (
  `id` int(11) NOT NULL,
  `totalPrice` int(11) DEFAULT NULL,
  `master_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `haircut_case_options`
--

CREATE TABLE `haircut_case_options` (
  `HaircutCase_id` int(11) NOT NULL,
  `options_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `haircut_types`
--

CREATE TABLE `haircut_types` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `haircut_types`
--

INSERT INTO `haircut_types` (`id`, `name`, `price`) VALUES
(1, 'Fade', 300),
(2, 'Undercut', 150),
(3, 'Pompadour', 400),
(4, 'Quiff', 350),
(5, 'Faux Hawk', 300);

-- --------------------------------------------------------

--
-- Структура таблицы `masters`
--

CREATE TABLE `masters` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `masters`
--

INSERT INTO `masters` (`id`, `name`) VALUES
(1, 'Van Darkholme'),
(2, 'Billy Herrington'),
(3, 'Danny Lee');

-- --------------------------------------------------------

--
-- Структура таблицы `options`
--

CREATE TABLE `options` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `options`
--

INSERT INTO `options` (`id`, `name`) VALUES
(1, 'Shower'),
(2, 'Barbing');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `haircut_case`
--
ALTER TABLE `haircut_case`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgf211ne0oued1fsf92kwohqbd` (`master_id`),
  ADD KEY `FKjrb40w9h3uphka9ibqswotr1o` (`type_id`);

--
-- Индексы таблицы `haircut_case_options`
--
ALTER TABLE `haircut_case_options`
  ADD PRIMARY KEY (`HaircutCase_id`,`options_id`),
  ADD KEY `FKl3wmut5jgy0fu1ug1l99fhoo7` (`options_id`);

--
-- Индексы таблицы `haircut_types`
--
ALTER TABLE `haircut_types`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `masters`
--
ALTER TABLE `masters`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `options`
--
ALTER TABLE `options`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `haircut_case`
--
ALTER TABLE `haircut_case`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `haircut_types`
--
ALTER TABLE `haircut_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `masters`
--
ALTER TABLE `masters`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `options`
--
ALTER TABLE `options`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
