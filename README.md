# Custom LLM Deployment POC

This project demonstrates how to deploy a fine-tuned language model locally with a Spring Boot backend and basic UI, ensuring data privacy through encryption.

## Features

- Fine-tuned LLM served via FastAPI
- Spring Boot backend with REST API
- Basic HTML/JS frontend
- Data encryption for privacy
- Local deployment for data security

## Setup

1. Clone this repository
2. Run the setup script: `./setup.sh`
3. Copy your fine-tuned model to `model/saved_model/`

## Running the System

1. Start the model server:
   ```bash
   cd model
   source venv/bin/activate
   python app.py