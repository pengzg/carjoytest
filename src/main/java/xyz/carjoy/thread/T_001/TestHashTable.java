package main.java.xyz.carjoy.thread.T_001;

import java.util.Hashtable;
import java.util.UUID;

public class TestHashTable {
    static Hashtable<UUID,UUID> m = new Hashtable<>();
    static int count = Constants.COUNT;

    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];

    static final int THREAD_COUNT = Constants.THREADCOUNT;
    
    // https://www.cnblogs.com/lukelook/p/11183155.html
    static {
        for (int i = 0; i < count; i++) {
             keys[i] =  UUID.randomUUID();
             values[i] = UUID.randomUUID();
        }
    }

}
