#  Lost in the Woods — Java Text Adventure Game

**Lost in the Woods** is a simple console-based text adventure game written in **Java**.  
The player wakes up alone in a dangerous forest and must navigate through different locations, collect weapons, fight wild animals, and reach the **Town** to survive.

This project was created as a learning exercise to practice:
- Object-Oriented Programming (OOP)
- Inheritance and abstraction
- Game loops and user input handling
- Basic combat mechanics
- Java collections (List, Map)

---

## Game Overview

You start in the **Forest Center**, surrounded by trees.  
Some paths are safe, others hide dangerous animals like lions and buffalos.

Your goal:
- Explore the forest
- Collect items (Knife, Sword, Machete)
- Fight or avoid animals
- Reach the **Town** to win

If your health reaches **0**, the game is over.

---

##  World Map (Conceptual)

- **Forest Center** (starting location)
  - South → Dark Path
  - West → Old Trail
- **Dark Path**
  - South → Lion's Den
- **Old Trail**
  - West → Town (WIN)
- **Lion's Den**
  - Dangerous area with a Lion

---

##  Combat System

- You can fight animals in two ways:
  - `fight` → bare hands (base attack power)
  - `use <item>` → weapon-based attack (item power)
- Animals attack back if they survive
- Killing an animal removes it from the location

---

## Items

Items increase your attack power when used:
- **Knife** (+10)
- **Sword** (+30)
- **Machete** (+40)

Items must be picked up before they can be used.

---

##  Available Commands

| Command | Description |
|------|------------|
| `go <direction>` | Move to another location |
| `north / south / east / west` | Shortcut movement |
| `back` | Return to previous location |
| `look` | Examine current location |
| `take <item>` | Pick up an item |
| `use <item>` | Use an item to attack an animal |
| `fight` | Fight an animal with bare hands |
| `inventory` / `i` | Show inventory and HP |
| `help` | Show all commands |
| `quit` | Exit the game |

---


##  Project Structure
Inthewoods/src/
│
├── Game.java # Main game loop and command handling
├── Player.java # Player logic and inventory
├── Animal.java # Enemy class
├── Creature.java # Base class for Player and Animal
├── Location.java # World locations and connections
├── Item.java # Weapons and items
├── Direction.java # Enum for movement directions
└── README.md

## How to Run the Game

### Requirements
- Java JDK 8 or higher
- Terminal / Command Prompt

### Steps
```bash
javac *.java
java Game





## 🧱 Project Structure

