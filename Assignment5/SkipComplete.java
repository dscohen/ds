public class SkipList : ICollection where T : IComparable
{
  private struct SkipListNode
  {
    private readonly N item;
    private readonly SkipListNode?[] next;

    public SkipListNode(N item, int depth)
    {
      this.item = item;
      next = new SkipListNode?[depth];
    }

    public N Item
    {
      get { return item; }
    }

    public SkipListNode?[] Next
    {
      get { return next; }
    }
  }

  private int count = 0;
  private int depth = 1;
  private SkipListNode head = new SkipListNode(default(T), 33);

  public bool Contains(T item)
  {
    SkipListNode cur = head;

    for (int level = depth – 1; level >= 0; level–)
    {
      for (; cur.Next[level] != null; cur = cur.Next[level].Value)
      {
        if (cur.Next[level].Value.Item.CompareTo(item) > 0)
        {
          break;
        }

        if (cur.Next[level].Value.Item.CompareTo(item) == 0)
        {
          return true;
        }
      }
    }

    return false;
  }

  public bool Remove(T item)
  {
    SkipListNode cur = head;

    bool found = false;

    for (int level = depth – 1; level >= 0; level–)
    {
      for (; cur.Next[level] != null; cur = cur.Next[level].Value)
      {
        if (cur.Next[level].Value.Item.CompareTo(item) == 0)
        {
          found = true;
          cur.Next[level] = cur.Next[level].Value.Next[level];
          count–;
          break;
        }

        if (cur.Next[level].Value.Item.CompareTo(item) > 0)
        {
          break;
        }
      }
    }

    return found;
  }

  public void Add(T item)
  {
    // Determine the new depth of this new node. Retrieve the hashcode for value. The number of
    // 1-bits before we encounter the first 0-bit is the level of the node. Since hashcode is
    // 32-bit, the level can be at most 32.
    int nodeLevel = 0;

    for (int hash = item.GetHashCode(); (hash & 1) == 1; hash >>= 1)
    {
      nodeLevel++;

      if (nodeLevel == depth)
      {
        depth++;
        break;
      }
    }

    // Insert this node into the skip list
    SkipListNode newNode = new SkipListNode(item, nodeLevel + 1);
    SkipListNode cur = head;

    for (int level = depth – 1; level >= 0; level–)
    {
      for (; cur.Next[level] != null; cur = cur.Next[level].Value)
      {
        if (cur.Next[level].Value.Item.CompareTo(item) > 0)
        {
          break;
        }
      }

      if (level <= nodeLevel)
      {
        newNode.Next[level] = cur.Next[level];
        cur.Next[level] = newNode;
        count++;
      }
    }
  }

  public void Clear()
  {
    head = new SkipListNode(default(T), 33);
    count = 0;
  }

  public void CopyTo(T[] array, int arrayIndex)
  {
    throw new NotImplementedException();
  }

  public int Count
  {
    get { return count; }
  }

  public bool IsReadOnly
  {
    get { return false; }
  }

  private IEnumerator GetTheEnumerator()
  {
    SkipListNode? cur = head.Next[0];

    while (cur != null)
    {
      yield return cur.Value.Item;
      cur = cur.Value.Next[0];
    }
  }

  public IEnumerator GetEnumerator()
  {
    return GetTheEnumerator();
  }

  System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator()
  {
    return GetTheEnumerator();
  }

}
