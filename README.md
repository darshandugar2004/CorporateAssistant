# Custom LLM Deployment for Confidential Corporate Data

This project demonstrates a practical application of a fine-tuned Large Language Model (LLM) for corporate data classification, with a strong focus on on-premise deployment and data privacy. It showcases a complete solution, from model fine-tuning to a secure, multi-component application architecture.

Model link : https://huggingface.co/darshandugar/MailClassifier-DistilBERT

## 🌟 Key Features

* **Custom LLM Fine-tuning:** A pre-trained `distilbert-base-uncased` model is fine-tuned on a custom dataset to categorize corporate emails.
* **Data Augmentation:** Addresses data imbalance issues by generating high-quality synthetic data using Groq's LLM, ensuring robust model performance on all categories.
* **On-Premise Deployment:** The entire system is designed to run on a local server, guaranteeing that sensitive data never leaves your controlled network.
* **Secure Communication:** Implements a basic encryption scheme for data packets sent between the application's backend and the model inference server, upholding a chain of data privacy.
* **Layered Architecture:** The system is composed of a decoupled Python model server, a Spring Boot backend API, and a basic HTML/JS frontend for a clear and maintainable design.

## 🧠 Fine-Tuning Methodology

This project utilizes a **full fine-tuning** approach. This technique is a standard and effective method for adapting a pre-trained model to a specific task.

1.  **Loading a Pre-trained Model:** The code loads the `distilbert-base-uncased` model. This model has already learned a deep understanding of general language from a massive corpus of text.
2.  **Modifying the Output Layer:** When the model is loaded with `AutoModelForSequenceClassification.from_pretrained(model_name, num_labels=num_labels)`, its original output layer is replaced with a new, randomly initialized layer. This new layer is specifically configured to output a prediction for each of the unique labels in our dataset.
3.  **Training the Entire Model:** The `transformers.Trainer` class then trains the model. Crucially, in full fine-tuning, **all** of the model's parameters—both in the original pre-trained layers and the new output layer—are updated. This allows the model to deeply adapt its entire learned structure to the specific task of classifying corporate emails.

## 📊 Data & Augmentation

* **Dataset Used:** The model is trained on the `infinite-dataset-hub/CorporateMailCategorization` dataset.
* **Addressing Imbalance:** Recognizing that some labels had very few instances, a key step was to augment the training data. A custom script was developed to use a powerful LLM (Groq) to generate additional synthetic examples for these under-represented categories. This balanced the dataset, which is crucial for achieving good and fair performance across all email categories.

## ⚙️ Architecture

The system follows a three-tier architecture:

* **Frontend:** A simple HTML/JavaScript UI allows users to submit an email query.
* **Backend:** A Spring Boot application serves as the main API. It handles user requests, creates encrypted data packets, and communicates with the model server.
* **Model Server:** A Python-based server powered by FastAPI hosts the fine-tuned LLM. It receives encrypted data, makes predictions, and sends the encrypted response back to the backend.

## 🚀 Setup

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/Corporate-Mail-Categorizer-POC.git](https://github.com/your-username/Corporate-Mail-Categorizer-POC.git)
    cd Corporate-Mail-Categorizer-POC
    ```
2.  **Set up the Python environment:**
    ```bash
    # Assuming you have a setup.sh script for this
    ./setup.sh
    ```
3.  **Copy your fine-tuned model:**
    After training the model using the provided notebooks, copy the saved model files (the `mail_category` directory) into `model/saved_model/`.

## ▶️ Running the System

1.  **Start the Python Model Server:**
    ```bash
    cd model
    source venv/bin/activate
    python app.py
    ```
2.  **Start the Spring Boot Backend:**
    ```bash
    cd ../spring-boot-backend
    ./mvnw spring-boot:run
    ```
3.  **Open the UI:**
    Open `frontend-ui/index.html` in your web browser.
