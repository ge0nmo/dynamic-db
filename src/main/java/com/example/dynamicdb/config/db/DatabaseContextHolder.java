package com.example.dynamicdb.config.db;

public class DatabaseContextHolder
{
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static String getBranch()
    {
        return CONTEXT.get();
    }

    public static void setBranch(String branch)
    {
        CONTEXT.set(branch);
    }

    public static void clearBranch()
    {
        CONTEXT.remove();
    }
}
