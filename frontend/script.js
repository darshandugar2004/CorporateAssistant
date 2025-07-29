document.addEventListener('DOMContentLoaded', function() {
    const submitBtn = document.getElementById('submitBtn');
    const resultContainer = document.getElementById('resultContainer');
    
    submitBtn.addEventListener('click', async function() {
        const userId = document.getElementById('userId').value.trim();
        const query = document.getElementById('query').value.trim();
        
        if (!userId || !query) {
            alert('Please enter both User ID and Query');
            return;
        }
        
        try {
            const startTime = new Date();
            
            const response = await fetch('http://localhost:8080/api/llm/query', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userId: userId,
                    query: query
                })
            });
            
            const data = await response.json();
            
            if (!data.success) {
                throw new Error(data.message);
            }
            
            const endTime = new Date();
            const processingTime = (endTime - startTime) / 1000;
            
            // Display results
            document.getElementById('resultUserId').textContent = data.data.userId;
            document.getElementById('resultQuery').textContent = data.data.processedQuery;
            document.getElementById('resultClass').textContent = data.data.predictedClass;
            document.getElementById('resultConfidence').textContent = `${(data.data.confidence * 100).toFixed(2)}%`;
            document.getElementById('resultTime').textContent = `${processingTime.toFixed(2)} seconds`;
            
            resultContainer.classList.remove('hidden');
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred: ' + error.message);
        }
    });
});