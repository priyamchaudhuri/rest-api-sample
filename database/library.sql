CREATE TABLE `address` (
  `addressId` varchar(11) NOT NULL,
  `addressLine1` varchar(20) DEFAULT NULL,
  `addressLine2` varchar(20) DEFAULT NULL,
  `addressLine3` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rack` (
  `id` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `shelfId` int(11) DEFAULT NULL,
  KEY `shelfId` (`shelfId`),
  CONSTRAINT `shelfId` FOREIGN KEY (`shelfId`) REFERENCES `shelf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `empId` int(11) DEFAULT NULL,
  KEY `id` (`id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `resource` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `publication` varchar(20) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `resource_mapping` (
  `resourceId` int(11) NOT NULL DEFAULT '0',
  `ownerId` int(11) NOT NULL DEFAULT '0',
  `ownerType` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`resourceId`,`ownerType`,`ownerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `shelf` (
  `id` int(11) NOT NULL,
  `location` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `student` (
  `id` int(11) DEFAULT NULL,
  `rollNo` int(11) DEFAULT NULL,
  KEY `fk_id` (`id`),
  CONSTRAINT `fk_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `fName` varchar(20) DEFAULT NULL,
  `lName` varchar(20) DEFAULT NULL,
  `joiningDate` date DEFAULT NULL,
  `addressId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addressId` (`addressId`),
  CONSTRAINT `fk_addressId` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

