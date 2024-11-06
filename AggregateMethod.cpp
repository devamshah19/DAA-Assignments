#include <iostream>

class DynamicArrayAggregate {
public:
    DynamicArrayAggregate() : capacity(1), size(0), total_cost(0) {
        array = new int[capacity];
    }

    ~DynamicArrayAggregate() {
        delete[] array;
    }

    void insert(int element) {
        if (size == capacity) {
            resize();
        }
        array[size] = element;
        size++;
        total_cost++;
    }

    double amortized_cost() const {
        return size > 0 ? static_cast<double>(total_cost) / size : 0;
    }

    void print_result() const {
        std::cout << "Total cost: " << total_cost
                  << ", Amortized cost per insertion: " << amortized_cost()
                  << std::endl;
    }

private:
    int* array;
    int capacity;
    int size;
    int total_cost;

    void resize() {
        int new_capacity = capacity * 2;
        int* new_array = new int[new_capacity];
        for (int i = 0; i < size; i++) {
            new_array[i] = array[i];
            total_cost++;
        }
        delete[] array;
        array = new_array;
        capacity = new_capacity;
    }
};

int main() {
    std::cout << "Aggregate Method Analysis:" << std::endl;
    DynamicArrayAggregate aggregate_array;
    for (int i = 0; i < 10; ++i) {
        aggregate_array.insert(i);
    }
    aggregate_array.print_result();
    return 0;
}
