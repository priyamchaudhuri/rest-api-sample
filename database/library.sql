CREATE TABLE `shelf` (
  `id` int(11) NOT NULL,
  `location` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `addressLine1` varchar(20) DEFAULT NULL,
  `addressLine2` varchar(20) DEFAULT NULL,
  `addressLine3` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  KEY `fk_userId` (`userId`),
  CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rack` (
  `id` int(11) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `shelfId` int(11) DEFAULT NULL,
  KEY `shelfId` (`shelfId`),
  CONSTRAINT `shelfId` FOREIGN KEY (`shelfId`) REFERENCES `shelf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `resource` (
  `id` int(11) NOT NULL,
  `name` int(11) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `publication` varchar(20) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `resource_mapping` (
  `resourceId` int(11) NOT NULL DEFAULT '0',
  `ownerId` int(11) NOT NULL DEFAULT '0',
  `ownerType` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`resourceId`,`ownerType`,`ownerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `fName` varchar(20) DEFAULT NULL,
  `lName` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `joinDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

