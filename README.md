# BuggyBlackjack

## Overview

BuggyBlackjack is a Java console application that simulates a simplified Blackjack card game. This project was completed as part of coursework at Southern Maine Community College (SMCC).

The primary goal of the assignment was to analyze, debug, and correct an existing program that contained multiple logical and programming errors. Through testing, debugging, and code inspection, issues affecting gameplay, card handling, betting, and user interaction were identified and fixed.

---

## Features

- Play multiple hands of Blackjack
- Place bets before each hand
- Random card dealing
- Dealer draws cards according to Blackjack rules
- Win, loss, and tie tracking
- Running total of player winnings
- Input validation for betting amounts
- Replay option after each hand

---

## Example Output

```text
Want to play a hand of blackjack?
y

How much do you want to bet on this hand?
5

My cards are : X 9
Your cards are : 6 J

Another card?
n

My cards are: 6 9
Next card for me is A
Next card for me is 10

I busted! You win!

At this time I owe you 5 dollars.

Play another?
```

---

## Bugs Identified and Fixed

During the debugging process, several issues were discovered and corrected, including:

### User Card Requests

Original code:

```java
userWantsACard = reply == 'y' && reply == 'Y';
```

Fixed logic:

```java
userWantsACard = reply == 'y' || reply == 'Y';
```

This allowed users to correctly request additional cards.

### Dealer Hand Calculation

The dealer's initial hand total was incorrectly calculated using the same card twice.

Original:

```java
sumOfDealerCards =
    valueOf(dealersHoleCard)
    + valueOf(dealersHoleCard);
```

Corrected to use both dealer cards.

### Betting Logic

The betting system was corrected to properly track wagers and update winnings.

### Game Flow

Fixed gameplay issues that prevented proper continuation and completion of multiple hands.

### Win/Loss Tracking

Corrected logic for recording player wins, losses, and ties.

---

## Technologies Used

- Java
- Apache NetBeans IDE 15
- Git
- GitHub

---

## Skills Demonstrated

- Java Programming
- Debugging Techniques
- Software Testing
- Problem Solving
- Object-Oriented Programming
- User Input Validation
- Version Control with Git and GitHub

---

## Academic Project

This project was completed as part of Java programming coursework at Southern Maine Community College (SMCC). The assignment focused on debugging an existing codebase, identifying logical errors, and implementing corrections to produce a fully functional Blackjack game.
