#!/bin/bash

# Create virtual environment for model server
echo "Setting up Python environment for model server..."
cd model
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
deactivate
cd ..

# Initialize Spring Boot backend
echo "Setting up Spring Boot backend..."
cd backend
./mvnw clean package
cd ..

echo "Setup complete!"
echo "To start the system:"
echo "1. Start the model server: cd model && source venv/bin/activate && python app.py"
echo "2. Start the Spring Boot backend: cd backend && ./mvnw spring-boot:run"
echo "3. Open frontend/index.html in your browser"