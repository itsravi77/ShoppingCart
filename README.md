# ShoppingCart

## Introduction
This application is about calculating the price of the shopping cart with items, currently it supports 2 items Orange and Apple.

The class SimpleBasketPricer is the main class that calculates the price of the items. It depends on ItemPricer to
provide the price of each individual item with it's quantity. There are 2 implementations of ItemPricer, one is SimpleItemPricer
which doesn't consider any offers and another one is ItemPricerWithOffers which has offers for Apple as buy one get one free
and Orange as 3 for the price of 2

## Prerequisite
In order to build and run the application, you will need following prerequisites

1. JDK 1.8
2. Maven 3+

## Build
Please run "mvn clean compile" to build the application

## Testing
Please run "mvn clean install" to build and test the application.

There are tests provided for the classes, which can be used to validate the implementation.
