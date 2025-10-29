package com.hfut_1.experimentQ;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 青蛙过河问题 - 动态规划解法
 * 
 * 问题描述：
 * 在河上有一座独木桥，一只青蛙想沿着独木桥从河的一侧跳到另一侧。
 * 在桥上有一些石子，青蛙很讨厌踩在这些石子上。
 * 青蛙从桥的起点(0)开始，一次跳跃的距离是S到T之间的任意正整数。
 * 当青蛙跳到或跳过坐标为L的点时，就算青蛙已经跳出了独木桥。
 * 
 * 关键优化：
 * 1. 当L很大(最大10^9)时，不能直接创建大小为L的数组
 * 2. 当两个石子之间的距离大于T*T时，可以压缩这段距离
 * 3. 因为在T*T的范围内，青蛙总能找到路径绕过这段区域内的任何石子
 * 
 * 动态规划：
 * dp[i] 表示从位置i到达终点最少需要踩到的石子数
 * 状态转移：dp[i] = min(dp[i+k] + (位置i有石子?1:0))，其中 S <= k <= T
 */
public class DP2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt(); // 桥的长度
        int S = sc.nextInt(); // 最小跳跃距离
        int T = sc.nextInt(); // 最大跳跃距离
        int M = sc.nextInt(); // 石子数量
        
        int[] stones = new int[M];
        for (int i = 0; i < M; i++) {
            stones[i] = sc.nextInt();
        }
        
        // 特殊情况：没有石子
        if (M == 0) {
            System.out.println(0);
            sc.close();
            return;
        }
        
        Arrays.sort(stones);
        
        // 关键优化：当两个石子之间的距离大于 T*T 时，可以压缩距离
        // 因为在这个范围内，青蛙总能找到路径绕过石子
        int maxGap = T * T;
        int[] compressedStones = new int[M];
        
        for (int i = 0; i < M; i++) {
            if (i == 0) {
                // 压缩起点到第一个石子的距离
                compressedStones[i] = Math.min(stones[i], maxGap);
            } else {
                int gap = stones[i] - stones[i - 1];
                if (gap > maxGap) {
                    compressedStones[i] = compressedStones[i - 1] + maxGap;
                } else {
                    compressedStones[i] = compressedStones[i - 1] + gap;
                }
            }
        }
        
        // 压缩后的最大位置
        int maxPos = compressedStones[M - 1] + T;
        
        // 将石子位置存入 HashSet 以便快速查询
        HashSet<Integer> stoneSet = new HashSet<>();
        for (int i = 0; i < M; i++) {
            stoneSet.add(compressedStones[i]);
        }
        
        // dp[i] 表示从位置 i 到达终点最少踩到的石子数
        int[] dp = new int[maxPos + 1];
        Arrays.fill(dp, M + 1); // 初始化为一个较大的值
        
        // 终点及之后的位置不需要踩石子
        for (int i = compressedStones[M - 1] + 1; i <= maxPos; i++) {
            dp[i] = 0;
        }
        
        // 从后向前动态规划
        for (int i = compressedStones[M - 1]; i >= 0; i--) {
            for (int k = S; k <= T; k++) {
                if (i + k <= maxPos) {
                    dp[i] = Math.min(dp[i], dp[i + k] + (stoneSet.contains(i) ? 1 : 0));
                }
            }
        }
        
        System.out.println(dp[0]);
        sc.close();
    }
}
