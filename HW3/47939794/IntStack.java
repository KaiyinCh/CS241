import java.util.Objects;

public class IntStack {
    private LinkedIntList data;
    public IntStack () {
        data = new LinkedIntList();
    }
    public IntStack(int... args) {
        data = new LinkedIntList(args);
    }
    public void push(int a) {
        //TODO: your code here
        data.add(a);
    }

    public int pop() {
        //TODO: your code here
        return data.getByIndex(data.size()-1);
    }

    public int peek_at(int idx) {
        //TODO: your code here
        return data.getByIndex(data.size()-1-idx);
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IntStack)) {
            return false;
        }
        LinkedIntList l1 = ((IntStack) other).data;
        LinkedIntList l2 = this.data;
        return l1.equals(l2);

    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}