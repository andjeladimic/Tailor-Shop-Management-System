# Tailor Shop Management System

A Java-based client-server application for managing tailor shop business operations,
developed as a university project at the Faculty of Organizational Sciences, University of Belgrade.

## About

The system enables tracking of invoices, clients, tailors, and tailoring
services through a clean three-tier architecture.

## Features

- **Authentication** — Tailor login with username and password
- **Invoice management** — Add, search, edit invoices with line items
- **Client management** — Add, search, edit, delete clients
- **Service management** — Add, search, edit, delete tailoring services

## Tech Stack

- **Language:** Java
- **IDE:** NetBeans 15
- **Database:** MySQL
- **Architecture:** 3-tier Client-Server (Sockets)
- **GUI:** Java Swing

## Architecture

The system is divided into three projects:

| Project | Role |
|---|---|
| `Klijent` | GUI forms and client-side controller |
| `Server` | Application logic, system operations, DB broker |
| `Zajednicki` | Shared domain model and communication objects |

## Database Setup

1. Open MySQL and create a new schema called `krojacka_radnja`
2. Import `krojacka_radnja.sql` from the `database/` folder
3. Update `dbconfig.properties` with your credentials

## How to Run

1. Import all three projects into NetBeans
2. Set up a MySQL database and configure `dbconfig.properties` on the server side
3. Start the **Server** project first
4. Start the **Klijent** project and log in

## Author

Anđela Dimić — Faculty of Organizational Sciences, University of Belgrade
