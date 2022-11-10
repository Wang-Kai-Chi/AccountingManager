-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 10, 2022 at 08:16 AM
-- Server version: 5.7.24
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `accounting_db01`
--

-- --------------------------------------------------------

--
-- Table structure for table `cash_flow_record`
--

CREATE TABLE `cash_flow_record` (
  `id` int(10) UNSIGNED NOT NULL,
  `amount_of_money` int(11) NOT NULL,
  `date` date NOT NULL,
  `category_id` tinyint(4) NOT NULL DEFAULT '30'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cash_flow_record`
--

INSERT INTO `cash_flow_record` (`id`, `amount_of_money`, `date`, `category_id`) VALUES
(1, 100, '2022-11-01', 1),
(2, 150, '2022-11-01', 1),
(3, 80, '2022-11-02', 2),
(4, 120, '2022-11-02', 2),
(5, 180, '2022-11-02', 3),
(6, 180, '2022-11-03', 1),
(7, 100, '2022-11-04', 1),
(8, 100, '2022-11-04', 1),
(9, 100, '2022-11-05', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cash_flow_record`
--
ALTER TABLE `cash_flow_record`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cash_flow_record`
--
ALTER TABLE `cash_flow_record`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
