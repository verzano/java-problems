package com.verzano.javaproblems.helper;

public final class Pair<T1, T2> {
  public final T1 first;
  public final T2 second;

  public Pair(T1 first, T2 second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Pair<?, ?> pair = (Pair<?, ?>) o;

    return (first != null ? first.equals(pair.first) : pair.first == null)
        && (second != null ? second.equals(pair.second) : pair.second == null);
  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + (second != null ? second.hashCode() : 0);
    return result;
  }
}
