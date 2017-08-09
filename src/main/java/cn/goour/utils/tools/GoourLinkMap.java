package cn.goour.utils.tools;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GoourLinkMap<K, V> implements Map<K, V>, Serializable, Cloneable {
	private Node<K,V> head;
	private Node<K,V> last;
	private int size;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GoourLinkMap() {
		head = new Node<K, V>(null, null, null);
		last = head;
		size=0;
	}
	
	public void clear() {
		last = head;
		head.next = null;
		size = 0;
	}

	public boolean containsKey(Object key) {
		Node<K,V> node = head;
		while((node = node.next) != null){
			if (node.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(Object value) {
		Node<K,V> node = head;
		while((node = node.next) != null){
			if (node.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return new AbstractSet<java.util.Map.Entry<K, V>>(){

			@Override
			public Iterator<java.util.Map.Entry<K, V>> iterator() {
				return new Iterator<java.util.Map.Entry<K, V>>() {
					private Node<K,V> node = head;
					public boolean hasNext() {
						return node.next != null;
					}

					public java.util.Map.Entry<K, V> next() {
						return node = node.next;
					}
				};
			}

			@Override
			public int size() {
				return size;
			}
			
		};
	}

	public V get(Object key) {
		Node<K,V> node = head;
		while((node = node.next) != null){
			if (node.getKey().equals(key)) {
				return node.getValue();
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Set<K> keySet() {
		Set<K> set = new TreeSet<K>();
		Node<K,V> node = head;
		while((node = node.next) != null){
			set.add(node.getKey());
		}
		return set;
	}

	public V put(K key, V value) {
		Node<K,V> node = new Node<K, V>(key, value, null);
		last.next = node;
		last = node;
		++size;
		return value;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		for (java.util.Map.Entry<? extends K, ? extends V> item:m.entrySet()) {
			put(item.getKey(), item.getValue());
		}
	}

	public V remove(Object key) {
		Node<K,V> node = head;
		Node<K,V> pre = head;
		V v = null;
		while((node = node.next) != null){
			if (node.getKey().equals(key)) {
				pre.next=node.next;
				v=node.getValue();
				--size;
			}else {
				pre = node;
			}
		}
		return v;
	}

	public int size() {
		return size;
	}

	public Collection<V> values() {
		Collection<V> list = new ArrayList<V>();
		Node<K,V> node = head;
		while((node = node.next) != null){
			list.add(node.getValue());
		}
		return list;
	}
	@Override
	public String toString() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("{");
		Node<K,V> node = head;
		while((node = node.next) != null){
			sBuffer.append(node.getKey());
			sBuffer.append("=");
			sBuffer.append(node.getValue());
			sBuffer.append(", ");
		}
		sBuffer.append("}");
		return sBuffer.toString();
	}
}
final class Node<K, V>
implements Map.Entry<K, V>{

    final K key;
    V value;
    Node<K, V> next;
    
    Node(K paramK, V paramV, Node<K, V> paramNode)
    {
      key = paramK;
      value = paramV;
      next = paramNode;
    }
	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}

	public V setValue(V value) {
		V v = this.value;
		this.value = value;
		return v;
	}
	
}