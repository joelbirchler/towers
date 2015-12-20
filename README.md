# Towers

A tile building cooperative board game.

## TODO

- Clean (move draw to render.cljs or some-such)
- Draw a character
- Ability to move a character
- Tile-placing cards
- Simple goals

...

- Second player (I think the board should always be angled POV to the character)


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

## Goal Cards



## Setup

Build your project once in dev mode with the following script and then open `index.html` in your browser.

    ./scripts/build

To auto build your project in dev mode:

    ./script/watch

To start an auto-building Node REPL (requires
[rlwrap](http://utopia.knoware.nl/~hlub/uck/rlwrap/), on OS X
installable via brew):

    ./scripts/repl

To get source map support in the Node REPL:

    lein npm install

To start a browser REPL:

1. Uncomment the following lines in src/towers/core.cljs:
```clojure
;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))
```
2. Run `./scripts/brepl`
3. Browse to `http://localhost:9000` (you should see `Hello world!` in the web console)
4. (back to step 3) you should now see the REPL prompt: `cljs.user=>`
5. You may now evaluate ClojureScript statements in the browser context.

For more info using the browser as a REPL environment, see
[this](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).

Clean project specific out:

    lein clean

Build a single release artifact with the following script and then open `index_release.html` in your browser.

    ./scripts/release

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
