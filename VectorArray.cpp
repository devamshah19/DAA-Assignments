#include <iostream>
#include <stdexcept>

class DynamicArray {
private:
    int* data;
    int capacity;
    int size;

    void resize(int new_capacity) {
        int* new_data = new int[new_capacity];
        for (int i = 0; i < size; ++i)
            new_data[i] = data[i];
        delete[] data;
        data = new_data;
        capacity = new_capacity;
    }

public:
    DynamicArray() : data(new int[2]), capacity(2), size(0) {}

    void push_back(int value) {
        if (size == capacity)
            resize(capacity * 2);
        data[size++] = value;
    }

    void pop_back() {
        if (size > 0)
            --size;
    }

    int& operator[](int index) {
        if (index >= 0 && index < size)
            return data[index];
        throw std::out_of_range("Index out of range");
    }

    int get_size() const {
        return size;
    }
};

int main() {
    DynamicArray arr;
    int arraySize = 10;
    for(int i=1;i<arraySize;i++)
        arr.push_back(i*2);

    for (int i = 0; i < arr.get_size(); ++i)
        std::cout << arr[i] << " ";
    std::cout << std::endl;

    arr.pop_back();

    for (int i = 0; i < arr.get_size(); ++i)
        std::cout << arr[i] << " ";
    std::cout << std::endl;

    return 0;
}
