# 99.7% Citric Liquid

## About

`99.7% Citric Liquid` is a simplified clone of the renowned game, `100% Orange Juice`. Its main
purpose is to serve as an educational tool, teaching foundational programming concepts.

📢 **Note**: This project is purely educational and will not be used for any commercial purposes.

---

## Tarea 1 - Juan Ignacio Molina: Specifications

In the following paragraphs, the implemented functionalities of the main structure
of the game are specified. 

The project is divided on two packages, Panels and Units. The Panels package contains
the constructors for the Panel objects, which are the components of a board on a game. The Units 
package contains the constructors for the Units, which are the Entities that interact in said board.
They can be either playable (Therefore dependant of users decisions) or non-playable. 

All the different Panels extend from the abstract Class "AbstractPanel" which itself extends from the "Panel" trait.
The Panel trait defines attributes that are shared amongst any Panel entity (characters, panels next to it, etc) plus 
methods that are shared as well.

The Units package contains the Units trait, which defines attributes and methods that are shared amongst any Unit
entity (Hit Points, Attack, etc). Those shared attributes and methods are implemented on an abstract class "AbstractUnit".
Since not all Units are playable, the Units trait has to be divided in another two classes. The first one is the PlayerCharacter
class. This class implements any attribute or method that is specific to a PlayerCharacter. The PlayerCharacter is used as a type
in some cases, since players can vary a lot and are unique to each user. The second class is the "Wild Unit". The trait "WildUnit"
is created so that it can be used as a type. Wild Units are non-playable entities that can fight the player.
The have set attributes, and have a particular behaviour different to playable entities. They are also divided in three 
different types, Chicken, RoboBall and Seagull, each of them with their own set attributes.


<div style="text-align:center;">
    <img src="https://i.creativecommons.org/l/by/4.0/88x31.png" alt="Creative Commons License">
</div>

This project is licensed under the [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/).

---