-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 25, 2021 at 07:37 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `number_of_people` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `date`, `message`, `number_of_people`, `status`, `time`, `user_id`) VALUES
(2, '2021-06-26', 'We want the best service', 5, 2, '15:20', 2);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` bigint(20) NOT NULL,
  `category` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(63) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `rank` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `category`, `description`, `image_url`, `name`, `price`, `rank`) VALUES
(1, 0, 'Two eggs with one sausage is a perfect breakfast', '1624651408.jpg', 'Fried Eggs', 3.99, 3),
(2, 0, 'Ordinary sandwich with extraordinary taste', '1624651696.jpg', 'Sandwich', 4.49, 2),
(3, 1, 'Special type of kebab which has been filled with the flavor of spices', '1624653326.jpg', 'Kebab', 7.99, 5),
(5, 2, 'The real meat cooked to the best', '1624653440.jpg', 'Steak', 12.99, 6),
(6, 2, 'Tomato sauce, firm mozzarella cheese, fresh soft mozzarella cheese, mushrooms', '1624653471.jpg', 'Pizza', 6.49, 4),
(7, 3, 'The jewel of desserts with exceptional taste', '1624653529.jpg', 'Tiramisu', 3.79, 7),
(8, 3, 'Pudding with the classic taste of chocolate', '1624653573.jpg', 'Pudding', 5.99, 5),
(9, 4, 'Cream + Cinnamon + Chocolate Powder = Cappucino', '1624653603.jpg', 'Cappucino', 1.29, 2),
(10, 4, 'Juice made from fresh fruits', '1624653633.jpg', 'Juice', 1.59, 3),
(11, 1, 'Potatoes, sauce and spices make the perfect fries', '1624654355.jpg', 'French Fries', 3.99, 3);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` bigint(20) NOT NULL,
  `created_at` varchar(255) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `created_at`, `review`, `user_id`) VALUES
(1, '2021-06-24 11:52', 'They have the best meals ever', 2),
(2, '2021-06-25 01:54', 'Cheap but heavenly', 4),
(3, '2021-06-25 10:36', 'I enjoy eating there very much. Thank you', 5);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(63) DEFAULT NULL,
  `last_name` varchar(63) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
(1, 'admin@gmail.com', 'Admin', 'Admin', '$2a$10$vQpxNuggPYp6TQrF0ADmQeIIlO5nYtiUmqlKvZv6ek3/ocj2TpCSy', 1),
(2, 'kamronbek@gmail.com', 'Kamronbek', 'Rustamov', '$2a$10$5OqiDHZKbFDVhH36l1fEUuoWAwqu/xPpzEpKeQ0VJO6eOAdqSoYJi', 0),
(3, 'jane@gmail.com', 'Jane', 'Doe', '$2a$10$pkTZr0HPci1sakskVJu.YePFCfjxucrsn39l7SmpseIPaWczE9cwO', 0),
(4, 'lucy@gmail.com', 'Lucy', 'King', '$2a$10$rM4NbnnCA0W2V9k41GGOGO7A4AOHr2DFzy63QeSOonCbmTlwFtSk.', 0),
(5, 'freda@gmail.com', 'Freda', 'Ford', '$2a$10$HzB8cuDXSHlBiDwSDD3Unuikg6Y1Z967eLAk6uCod/7RbwbCVWnMK', 0),
(7, 'nicol@gmail.com', 'Nicol', 'Green', '$2a$10$xrTdb0DKwAQvQPI.YnERL.11gKu0P1ZKbMMUhLh5VHWtIWN4Bd8om', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7udbel7q86k041591kj6lfmvw` (`user_id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6cpw2nlklblpvc7hyt7ko6v3e` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `FK7udbel7q86k041591kj6lfmvw` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK6cpw2nlklblpvc7hyt7ko6v3e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
