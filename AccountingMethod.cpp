#include <iostream>

class DynamicArrayAccounting {
public:
    DynamicArrayAccounting() : capacity(1), size(0), total_cost(0), credit(0) {
        array = new int[capacity];
    }

    ~DynamicArrayAccounting() {
        delete[] array;
    }

    void insert(int element) {
        if (size == capacity) {
            resize();
        }
        array[size] = element;
        size++;
        total_cost += 3;
        credit += 2;
    }

    double amortized_cost() const {
        return size > 0 ? static_cast<double>(total_cost) / size : 0;
    }

    void print_result() const {
        std::cout << "Total cost: " << total_cost
                  << ", Amortized cost per insertion: " << amortized_cost()
                  << ", Remaining credit: " << credit << std::endl;
    }

private:
    int* array;
    int capacity;
    int size;
    int total_cost;
    int credit;

    void resize() {
        int new_capacity = capacity * 2;
        int* new_array = new int[new_capacity];
        for (int i = 0; i < size; i++) {
            new_array[i] = array[i];
            credit--;
        }
        delete[] array;
        array = new_array;
        capacity = new_capacity;
    }
};

int main() {
    std::cout << "Accounting Method Analysis:" << std::endl;
    DynamicArrayAccounting accounting_array;
    for (int i = 0; i < 10; ++i) {
        accounting_array.insert(i);
    }
    accounting_array.print_result();
    return 0;
}

