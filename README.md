# Railway Network MST (Minimum Spanning Tree) Project

## Overview

This project implements **Prim's** and **Kruskal's algorithms** to compute the **Minimum Spanning Tree (MST)** for a graph where:
- **Nodes represent major US cities**, and
- **Edges represent potential railroads between the cities**, with edge weights proportional to the distance between cities.

The goal of the project is to assist a new railway company in determining the minimum cost to connect all cities with a train network. The cost of connecting two cities is proportional to the distance between them, and this MST helps to provide an initial estimate for the route costs.

## Features

- Graphical User Interface (GUI) to display cities on the map.
- Visualization of the Minimum Spanning Tree connecting the cities.
- Supports both **Prim's** and **Kruskal's algorithms** to compute the MST.
- Reads data from an input text file (`USA.txt`), where city coordinates and connections are stored.

## Algorithms Used

### Prim's Algorithm

Prim's algorithm starts with a single node and repeatedly adds the smallest edge that connects the growing spanning tree to a new vertex.

### Kruskal's Algorithm

Kruskal's algorithm sorts all edges in the graph by weight and then adds edges to the MST in increasing order of weight, provided it doesn't form a cycle.

### `USA.txt` Format

The input file contains information about cities and the distances between them. Each line specifies the following:
- City 1, City 2, Distance

Example format:
New York, Los Angeles, 2445 Chicago, Houston, 1086 Boston, Seattle, 2500 ...


## Running the Project

### Prerequisites

- Java (JDK 8+)
- A suitable IDE or terminal to run Java applications

### Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/railway-mst.git
   cd railway-mst
2. Place the input file: Ensure the USA.txt file is in the input/ folder. This file should contain the cities and distances in the format mentioned above.

3. Compile and run the application: You can compile the program using the following commands in the terminal:
  ```bash
  javac src/MSTDriver.java
  java src/MSTDriver input/USA.txt
 ```

4. Graphical User Interface: The program will open a GUI that displays the graph of cities and the computed Minimum Spanning Tree, highlighting the optimal connections between cities.


## How the Program Works
The program reads the input data from the USA.txt file, representing a graph of cities and potential railroads.
It computes the MST using both Prim's and Kruskal's algorithms, selecting the one that is more efficient based on the graph structure.
The results are displayed visually in a GUI, where users can see the cities and the railroads that make up the MST.


