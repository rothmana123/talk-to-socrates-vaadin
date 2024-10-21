# Morocco Noir

**Morocco Noir** is a mystery adventure game inspired by *Where in the World is Carmen Sandiego?*, powered by OpenAI’s GPT-4. The player takes on the role of a detective in search of a famous historical figure hiding somewhere in Morocco. Through conversations with locals, the player gathers clues and evidence in different Moroccan cities to identify the suspect before time runs out.

## Features
- Explore 5 iconic cities in Morocco: Casablanca, Marrakech, Fes, Tangiers, and Rabat.
- Engage in conversations with market merchants and café owners for clues.
- Use clues to track down the famous historical figure hiding in Morocco.
- Powered by OpenAI GPT-4 to generate dynamic market conversations.
- Predefined café conversations with unique hints in each city.

## How to Play
1. Start in Casablanca and gather clues by visiting the market or a café.
2. Travel between cities using the taxi option, choosing your destinations wisely.
3. Use the clues to piece together the identity of the hidden historical figure.
4. Submit your guess when you think you know who the suspect is.

## Requirements
- OpenAI API Secret Key (GPT-4)
- A valid `.env` file with your API key set.

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/morocco-noir.git
    cd morocco-noir
    ```

2. **Set up your environment**:
    Create a `.env` file in the root of your project and add your OpenAI secret API key like this:
    ```
    SECRET=your_openai_api_key_here
    ```

3. **Run the application**:
    Depending on your setup, use the appropriate command to run the application.

## Game Structure

- **Cities**: The game features five cities in Morocco. Each city offers its own set of market and café clues.
  - **Casablanca**: Vibrant souks and red sandstone buildings.
  - **Marrakech**: Ancient medina with a labyrinth of streets.
  - **Fes**: A city where the Mediterranean meets the Atlantic.
  - **Tangiers**: A blend of modern and traditional cultures.
  - **Rabat**: The capital where history meets contemporary art.

- **Clues and Evidence**: Gather clues by visiting the market and café in each city. Markets provide dynamically generated clues using OpenAI’s GPT-4, while café clues are pre-set and give valuable hints about the suspect’s next move.

- **Game Flow**:
  1. Start in Casablanca.
  2. Gather clues at the market or café.
  3. Travel between cities to collect more evidence.
  4. After gathering enough evidence, submit your guess to identify the suspect.

## Customization

### OpenAI API
The game leverages OpenAI's GPT-4 to generate market conversations dynamically. Make sure to add your OpenAI secret key to the `.env` file to enable this functionality.

```env
SECRET=your_openai_api_key
