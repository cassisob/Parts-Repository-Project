# Parts Repository Project - Java™ Remote Method Invocation (RMI)

This project is an implementation of a distributed system using **Java Remote Method Invocation (RMI)** to manage a repository of parts or components. It was developed as part of the **Distributed Systems** course in the **Analysis and Systems Development** program at **IFRS - Campus Farroupilha**.

## Project Overview

The system consists of a **server** that manages a repository of parts, where each part is represented as an object that implements the `Part` interface. The repository is managed by objects implementing the `PartRepository` interface, which stores and handles operations on parts.

### Parts (`Part`)

Each part is an object that encapsulates the following information:

- **Part code**: A unique identifier automatically generated when the part is added to the repository.
- **Part name**: The name of the part.
- **Part description**: A brief description of the part.
- **List of subcomponents**: A list of pairs `(subPart, quantity)` where `subPart` refers to a subcomponent (also a `Part` object), and `quantity` indicates how many units of the subcomponent are used in this part.

A part can be either **primitive** (having no subcomponents) or **composite**, containing a list of subcomponents. The subcomponents may be hosted on different servers, allowing the system to distribute the parts across multiple repositories.

### Part Repository (`PartRepository`)

The `PartRepository` manages a collection of `Part` objects. It allows operations such as:

- Adding a new part to the repository.
- Retrieving a part by its code.
- Listing all parts in the repository.

### Client

The client program interacts with the repository and the parts through the server. It allows the user to:

- Connect to a remote `PartRepository`.
- Retrieve and display information about the repository, such as its name and the number of parts.
- List all parts in the repository.
- Search for a part by its code.
- Add new parts (primitive or composite) to the repository.
- Interact with individual parts, displaying their name, description, and structure (whether they are primitive or composite, and listing their subcomponents).

## Technologies

- **Java RMI**: The core framework used to implement remote method invocation for distributed objects.
- **Java SE**: Standard Java development environment for building the server, client, and repository.