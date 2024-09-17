# Android RecyclerView Grouped Items App

This Android project demonstrates how to implement a RecyclerView that displays grouped items fetched from a remote API. The app uses **MVVM architecture**, **Retrofit** for network calls, and **Kotlin coroutines** for asynchronous operations.

## 🚀 Features

- ✅ Fetch data from a remote API
- ✅ Group items by `listId`
- ✅ Display items in a RecyclerView with headers for each group
- ✅ Filter out items with blank or null names
- ✅ Sort groups by `listId` and items within groups by name

## 🏗 Project Structure

The project consists of the following main components:

- **ItemAdapter**: A custom RecyclerView adapter that handles the display of grouped items with headers.
- **ItemViewModel**: ViewModel that fetches and processes the data.
- **Item**: Data class representing an item from the API.
- **ApiService** and **RetrofitClient**: Network-related classes for API communication.

## 🛠 How It Works

1. **ItemViewModel** fetches data from the API using Retrofit.
2. The retrieved data is processed to:
   - Filter out items with blank or null names.
   - Group items by their `listId`.
   - Sort the groups and items within each group.
3. The processed data is exposed as **LiveData** to be observed by the UI.
4. **ItemAdapter** takes the grouped data and creates a flat list of headers and items for display in the RecyclerView.

## 🔧 Setup
To use this project:

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git

Open the project in Android Studio
Sync the project with Gradle files
Run the app on an emulator or physical device

📚 Dependencies

Retrofit: For network requests
Gson: For JSON parsing
AndroidX ViewModel and LiveData: For implementing MVVM architecture
RecyclerView: For displaying the list of items

🌐 API
The app fetches data from the following endpoint:
Copyhttps://fetch-hiring.s3.amazonaws.com/hiring.json
🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.
📄 License
[Add your chosen license here]

Built with ❤️ by [SURBHI SHARMA]
