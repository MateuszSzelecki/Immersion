import math
import os


def merge_sort_pom(array, left, right, mid):
    left_half = array[left:mid+1]
    right_half = array[mid+1:right+1]

    i = j = 0
    k = left

    while i < len(left_half) and j < len(right_half):
        if left_half[i] < right_half[j]:
            array[k] = left_half[i]
            i += 1
        else:
            array[k] = right_half[j]
            j += 1
        k += 1

    while i < len(left_half):
        array[k] = left_half[i]
        i += 1
        k += 1

    while j < len(right_half):
        array[k] = right_half[j]
        j += 1
        k += 1

def merge_sort(array, left, right):
    if left >= right:
        return
    mid = (left+right) // 2
    merge_sort(array, left, mid)
    merge_sort(array, mid+1, right)
    merge_sort_pom(array, left, right, mid)


def partition(array, l, r):
    x = array[r]
    i = l-1
    for j in range(l, r):
        if array[j] < x:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i+1], arr[r] = arr[r], arr[i+1]
    return i+1

def quick_sort(array, l, r):
    if l < r:
        q = partition_homera(array, l, r)
        quick_sort(array, l, q-1)
        quick_sort(array, q+1, r)


def partition_homera(array, l, r):
    x = array[r-1]
    i = l-1
    j = r+1
    while True:
        i += 1
        while array[i] < x:
            i +=1
        j -= 1
        while array[j] > x:
            j -= 1
        if i >= j:
            return j
        array[i], array[j] = array[j], array[i]

def quick_sort_homera(array, l, r):
    if l < r:
        q = partition_homera(array, l, r)
        quick_sort(array, l, q)
        quick_sort(array, q+1, r)


def quick_sort_second(array):
    if len(array) <= 1:
        return array
    pivot = array[0]
    left = [x for x in array[1:] if x < pivot]
    equal = [x for x in array if x == pivot]
    right = [x for x in array[1:] if x > pivot]

    sorted = quick_sort_second(left) + equal + quick_sort_second(right)
    print(*sorted)
    return sorted


def counting_sort(A, B, k): #tablica A ma liczby całkowite z przedziału 0 ... k
                            #tablica B - wynikowa o rozmiarze identycznym z rozmiarem k
                            #k to maksymalna wartosc z tablicy A
    C = [0] * (k+1)
    for j in range (len(A)):
        C[A[j]] += 1 #zlicza ile jest danego elementu w tablicy A
    for i in range (1, k+1):
        C[i] += C[i-1]
    for j in range (len(A)-1, -1, -1):
        B[C[A[j]]-1] = A[j]
        C[A[j]] -= 1
    print(*B)


def insertion_sort(A):
    for i in range(1, len(A)):
        key = A[i]
        j = i-1
        while j >= 0 and A[j] > key:
            A[j+1] = A[j]
            j -= 1
        A[j+1] = key


def bucket_sort(arr):
    n = len(arr)
    B = [[] for i in range(n)]
    for i in range (n):
        index = math.floor(n * arr[i])
        B[index].append(arr[i])
    for i in range(n):
        insertion_sort(B[i])
    result = []
    for i in range(n):
        result.extend(B[i])
    print(*result)


arr = [0.79, 0.13, 0.16, 0.64, 0.39, 0.20, 0.89, 0.53, 0.71, 0.42]
bucket_sort(arr)

