# a09-akari

This is a recreation of the popular Japanese game, Akari. 

Akari is a logic puzzle that involves lighting up cells in a grid according to specific rules. The objective is to illuminate all the cells in the grid while following these rules:

1. Grid: Akari is played on a rectangular grid consisting of cells. Each cell can be either empty or contain a number from 0 to 4. Empty cells are represented by dots.

2. Lights: The goal is to place lights (represented by numbers) in some of the empty cells so that every empty cell is illuminated.

3. Illumination: A cell is considered illuminated if it contains a light or is reached by a direct path of lights from any direction (up, down, left, or right) without being blocked by a black cell or the edge of the grid.

4. Numbers: The numbers in the grid indicate how many lights must be placed in the neighboring cells. The neighboring cells are the cells directly adjacent to the number cell in horizontal and vertical directions.

5. Black Cells: Some cells in the grid may be black. Black cells cannot contain lights, and lights cannot illuminate black cells. They act as barriers that block the light.

6. Light Placement: Lights can only be placed in empty cells, and each empty cell can contain at most one light.

7. Adjacent Lights: Lights cannot be placed in cells that are adjacent (horizontally or vertically) to each other. In other words, lights must be separated by at least one empty cell or a black cell.

8. Clue Constraints: The number of lights in neighboring cells must match the clue numbers. For example, if a cell contains the number "3," exactly three neighboring cells must contain lights.

9. Uniqueness: There is only one valid solution for each Akari puzzle.

To solve an Akari puzzle, you need to strategically place lights based on the given clues, ensuring that all empty cells are illuminated while following the rules mentioned above.
