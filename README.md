# Towers

A tile building cooperative board game.

## TODO

- Ability to move a character
  - I don't like the world data structure that makes us repeat x y z in data and shape
    (maybe just a single flat list of tiles would be better)
  - Neighbors
  - Some kind of command-patternish thing to modify the hero pos
- Tile-placing cards
- Simple goals

...

- Second player (I think the board should always be angled POV to the character)


## Building and Running

I'm using the boot template from here: https://github.com/adzerk-oss/boot-cljs-example.

To get going:
> boot dev
(visit http://localhost:3000/)

To repl:
> boot repl --client
> boot.user=> (start-repl)
(reload the page)


## Overview

Here's the unbaked idea:

We have a small tile-based world (5 x 5 x ?) and two players. Players have a deck
of cards and a hand (5 cards). Some cards are actions (place a tile of a certain
type, jump, swap positions) and some cards are goals (eg. line up tiles in a certain
pattern). A player can also choose to move their character one tile on the board.

Players take turns. During their turn they can perform 3 moves-- either moving the
character or playing an action card.

Players score by achieving a goal in their hand.

Scores are *shared* amongst players, but hands (and therefore goals) are secret.


## Action Cards

- place various block types
- jump
- power jump


## Goal Cards



## MIT License

Copyright © 2016 Joel Birchler

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
