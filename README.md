# Problem Statement
Design a ReadWriteLock that allows multiple readers to read simultaneously but only one writer to modify data at the 
same time. The lock should provide 4 methods: `acquireReadLock()`, `releaseReadLock()`, `acquireWriteLock()`, 
`releaseWriteLock()`.

## Solution Approach:
1. It is assumed that a thread that acquires a read lock will not attempt to write data.
2. A HashSet is used to keep track of threads which are currently holding read lock
3. A variable is used to track the current holder of write lock
4. The lock is assumed to be reentrant.
5. If a thread which does not hold the lock attempts to release it, an `IllegalStateException` is thrown
6. Readers can acquire a read lock as long as the writer id is -1.
7. Writers can only acquire write locks if set of readers is empty, and writer id is not -1 and not same as its own id.

## Sample Output
```
Created initial database: [0, 1, 2, 3, 4]
Creating 9 readers and 3 writers
Reader-1 read data at 2 = 2
Reader-4 read data at 4 = 4
Reader-5 read data at 3 = 3
Reader-3 read data at 2 = 2
Reader-6 read data at 3 = 3
Reader-8 read data at 3 = 3
Reader-9 read data at 1 = 1
Reader-2 read data at 0 = 0
Reader-7 read data at 0 = 0
Writer-2 updated value at 0 from 0 to 1263791564
Writer-1 updated value at 0 from 1263791564 to 1376441835
Writer-3 updated value at 4 from 4 to 1387456601
Writer-3 updated value at 4 from 1387456601 to 624606832
Writer-3 updated value at 1 from 1 to -542144318
Writer-2 updated value at 2 from 2 to 524094723
Writer-1 updated value at 3 from 3 to -1280834440
Writer-2 updated value at 1 from -542144318 to 335902020
Writer-3 updated value at 1 from 335902020 to 753017639
Writer-1 updated value at 0 from 1376441835 to -400848217
Writer-2 updated value at 0 from -400848217 to 64499143
Reader-1 read data at 0 = 64499143
Reader-7 read data at 0 = 64499143
Reader-5 read data at 2 = 524094723
Writer-2 updated value at 0 from 64499143 to 1233808814
Writer-3 updated value at 3 from -1280834440 to 1739627476
Writer-1 updated value at 0 from 1233808814 to 1736086300
Writer-1 updated value at 3 from 1739627476 to -1039325915
Reader-4 read data at 2 = 524094723
Writer-3 updated value at 0 from 1736086300 to 100549904
Writer-1 updated value at 0 from 100549904 to 718380925
Writer-2 updated value at 1 from 753017639 to 1169646241
Reader-2 read data at 0 = 718380925
Reader-9 read data at 2 = 524094723
Reader-3 read data at 0 = 718380925
Writer-3 updated value at 0 from 718380925 to -89591206
Reader-8 read data at 4 = 624606832
Writer-2 updated value at 3 from -1039325915 to 587400496
Reader-6 read data at 2 = 524094723
Writer-1 updated value at 0 from -89591206 to -1736797146
Writer-3 updated value at 1 from 1169646241 to -489343380
Writer-2 updated value at 0 from -1736797146 to 782880700
Writer-1 updated value at 1 from -489343380 to -1911401808
Writer-2 updated value at 3 from 587400496 to 1158390852
Writer-1 updated value at 1 from -1911401808 to -1649411625
Writer-3 updated value at 1 from -1649411625 to -196091611
Reader-1 read data at 0 = 782880700
Writer-1 updated value at 3 from 1158390852 to 359830494
Reader-7 read data at 3 = 359830494
Writer-2 updated value at 2 from 524094723 to 1416153542
Reader-5 read data at 2 = 1416153542
Writer-3 updated value at 0 from 782880700 to -607514865
Writer-1 updated value at 1 from -196091611 to -1699855117
Writer-2 updated value at 0 from -607514865 to -111856875
Reader-3 read data at 2 = 1416153542
Reader-4 read data at 0 = -111856875
Reader-8 read data at 1 = -1699855117
Writer-2 updated value at 2 from 1416153542 to -1121214143
Writer-1 updated value at 4 from 624606832 to 860569522
Reader-2 read data at 4 = 860569522
Writer-3 updated value at 4 from 860569522 to -1353296841
Writer-1 updated value at 0 from -111856875 to -1980409731
Writer-2 updated value at 0 from -1980409731 to -143912631
Reader-9 read data at 2 = -1121214143
Reader-6 read data at 1 = -1699855117
Writer-1 updated value at 4 from -1353296841 to -1848944766
Writer-3 updated value at 4 from -1848944766 to -1248197373
Writer-2 updated value at 0 from -143912631 to 1562997318
Writer-2 updated value at 3 from 359830494 to 1261081903
Reader-3 read data at 0 = 1562997318
Reader-7 read data at 2 = -1121214143
Writer-1 updated value at 4 from -1248197373 to 969192133
Writer-3 updated value at 1 from -1699855117 to -962978521
Reader-1 read data at 1 = -962978521
Writer-2 updated value at 1 from -962978521 to -218440640
Writer-3 updated value at 2 from -1121214143 to 2074459576
Reader-5 read data at 3 = 1261081903
Reader-2 read data at 0 = 1562997318
Reader-8 read data at 2 = 2074459576
Writer-3 updated value at 4 from 969192133 to -1518183028
Writer-1 updated value at 0 from 1562997318 to -53882000
Reader-9 read data at 4 = -1518183028
Writer-2 updated value at 2 from 2074459576 to 410488415
Reader-6 read data at 1 = -218440640
Writer-1 updated value at 3 from 1261081903 to 1998693825
Writer-3 updated value at 0 from -53882000 to -1145387206
Reader-4 read data at 1 = -218440640
Writer-1 updated value at 4 from -1518183028 to 1998226622
Writer-1 updated value at 2 from 410488415 to -1892064053
Writer-2 updated value at 1 from -218440640 to -716310259
Writer-3 updated value at 0 from -1145387206 to 471711797
Writer-1 updated value at 4 from 1998226622 to 636835616
Writer-2 updated value at 0 from 471711797 to 1101615890
Writer-3 updated value at 4 from 636835616 to -90974334
Writer-1 updated value at 0 from 1101615890 to 1371137813
Writer-2 updated value at 0 from 1371137813 to -1781308782
Reader-7 read data at 0 = -1781308782
Reader-3 read data at 1 = -716310259
Writer-1 updated value at 3 from 1998693825 to 1655315793
Writer-3 updated value at 1 from -716310259 to -1980510405
Reader-2 read data at 2 = -1892064053
Writer-2 updated value at 4 from -90974334 to -793804882
Writer-1 updated value at 2 from -1892064053 to 1051693151
Writer-3 updated value at 2 from 1051693151 to 1035578564
Reader-6 read data at 4 = -793804882
Reader-9 read data at 1 = -1980510405
Reader-1 read data at 2 = 1035578564
Writer-1 updated value at 2 from 1035578564 to -1953420186
Reader-8 read data at 4 = -793804882
Writer-2 updated value at 3 from 1655315793 to -1248004805
Writer-3 updated value at 2 from -1953420186 to 1077429919
Reader-4 read data at 2 = 1077429919
Reader-5 read data at 2 = 1077429919
Writer-1 updated value at 2 from 1077429919 to -113579591
Writer-2 updated value at 3 from -1248004805 to -2142140397
Writer-3 updated value at 3 from -2142140397 to -241040296
Reader-3 read data at 1 = -1980510405
Writer-1 updated value at 1 from -1980510405 to 237640207
Writer-2 updated value at 0 from -1781308782 to 908155069
Writer-3 updated value at 3 from -241040296 to -1754248139
Reader-2 read data at 2 = -113579591
Writer-2 updated value at 3 from -1754248139 to 1906704139
Reader-7 read data at 3 = 1906704139
Reader-9 read data at 1 = 237640207
Writer-3 updated value at 3 from 1906704139 to 785207930
Writer-1 updated value at 4 from -793804882 to 2044640285
Reader-1 read data at 1 = 237640207
Reader-6 read data at 1 = 237640207
Writer-2 updated value at 0 from 908155069 to -1398573805
Writer-3 updated value at 1 from 237640207 to 452651018
Writer-1 updated value at 0 from -1398573805 to -101955327
Reader-4 read data at 2 = -113579591
Writer-3 updated value at 1 from 452651018 to -645553417
Writer-2 updated value at 2 from -113579591 to 1830486757
Reader-3 read data at 2 = 1830486757
Reader-8 read data at 3 = 785207930
Writer-1 updated value at 3 from 785207930 to 1956964764
Writer-2 updated value at 0 from -101955327 to 1274218505
Reader-5 read data at 4 = 2044640285
Writer-3 updated value at 1 from -645553417 to -123998043
Reader-2 read data at 1 = -123998043
Writer-2 updated value at 1 from -123998043 to 1502016858
Writer-3 updated value at 1 from 1502016858 to -1173582444
Writer-1 updated value at 1 from -1173582444 to 2097107791
Writer-3 updated value at 4 from 2044640285 to -3171660
Reader-6 read data at 1 = 2097107791
Reader-9 read data at 0 = 1274218505
Writer-2 updated value at 4 from -3171660 to -1149815296
Writer-3 updated value at 2 from 1830486757 to -728952230
Reader-1 read data at 3 = 1956964764
Writer-1 updated value at 2 from -728952230 to 2111249455
Writer-2 updated value at 1 from 2097107791 to -1385980680
Reader-8 read data at 4 = -1149815296
Reader-7 read data at 1 = -1385980680
Writer-2 updated value at 4 from -1149815296 to 1299935119
Reader-2 read data at 1 = -1385980680
Reader-4 read data at 0 = 1274218505
Writer-3 updated value at 1 from -1385980680 to 237263228
Reader-3 read data at 4 = 1299935119
Writer-1 updated value at 2 from 2111249455 to -1165394418
Writer-3 updated value at 3 from 1956964764 to -151992019
Writer-2 updated value at 4 from 1299935119 to 507325587
Reader-5 read data at 1 = 237263228
Writer-1 updated value at 3 from -151992019 to -767866233
Writer-2 updated value at 4 from 507325587 to 67590437
Writer-3 updated value at 2 from -1165394418 to 721274480
Reader-6 read data at 4 = 67590437
Writer-1 updated value at 2 from 721274480 to -1918290029
Reader-8 read data at 1 = 237263228
Reader-1 read data at 3 = -767866233
Writer-2 updated value at 3 from -767866233 to 2099158635
Writer-3 updated value at 3 from 2099158635 to 1619139331
Writer-1 updated value at 4 from 67590437 to -714240346
Reader-9 read data at 4 = -714240346
Writer-2 updated value at 4 from -714240346 to 1346939893
Reader-4 read data at 4 = 1346939893
Writer-3 updated value at 2 from -1918290029 to 440055863
Reader-3 read data at 0 = 1274218505
Writer-1 updated value at 2 from 440055863 to -273121435
Writer-2 updated value at 2 from -273121435 to -574015317
Writer-1 updated value at 3 from 1619139331 to -1516625
Reader-7 read data at 4 = 1346939893
Writer-3 updated value at 3 from -1516625 to 1345573329
Reader-2 read data at 3 = 1345573329
Writer-1 updated value at 0 from 1274218505 to -1385812427
Reader-1 read data at 0 = -1385812427
Writer-2 updated value at 3 from 1345573329 to 1184989660
Writer-2 updated value at 1 from 237263228 to 228710189
Reader-5 read data at 4 = 1346939893
Writer-3 updated value at 1 from 228710189 to -168514986
Writer-1 updated value at 1 from -168514986 to 306358972
Writer-2 updated value at 1 from 306358972 to -878786002
Writer-2 updated value at 4 from 1346939893 to 1902552094
Reader-8 read data at 2 = -574015317
Reader-9 read data at 4 = 1902552094
Reader-6 read data at 1 = -878786002
Writer-3 updated value at 0 from -1385812427 to -1508845940
Writer-1 updated value at 1 from -878786002 to 1299482806
Writer-2 updated value at 3 from 1184989660 to -777172453
Writer-2 updated value at 0 from -1508845940 to 1116338272
Writer-1 updated value at 1 from 1299482806 to 2135385015
Reader-3 read data at 0 = 1116338272
Reader-4 read data at 1 = 2135385015
Writer-3 updated value at 3 from -777172453 to -1844725045
Writer-2 updated value at 0 from 1116338272 to -1821157096
Writer-1 updated value at 2 from -574015317 to -1283071764
Reader-5 read data at 0 = -1821157096
Writer-1 updated value at 1 from 2135385015 to 2060256954
Reader-7 read data at 1 = 2060256954
Writer-3 updated value at 1 from 2060256954 to 1160276359
Writer-2 updated value at 2 from -1283071764 to 1643607509
Reader-2 read data at 0 = -1821157096
Reader-1 read data at 1 = 1160276359
Writer-3 updated value at 1 from 1160276359 to 1168082792
Reader-9 read data at 1 = 1168082792
Writer-2 updated value at 2 from 1643607509 to 1420205442
Writer-1 updated value at 3 from -1844725045 to 117416559
Reader-8 read data at 4 = 1902552094
Reader-3 read data at 1 = 1168082792
Writer-3 updated value at 2 from 1420205442 to -1734557638
Writer-3 updated value at 2 from -1734557638 to -1231621225
Writer-1 updated value at 0 from -1821157096 to -1445738431
Reader-4 read data at 4 = 1902552094
Reader-6 read data at 2 = -1231621225
Writer-2 updated value at 2 from -1231621225 to 1252222548
Reader-5 read data at 4 = 1902552094
Writer-3 updated value at 2 from 1252222548 to -905862265
Writer-1 updated value at 1 from 1168082792 to -903283373
Writer-3 updated value at 1 from -903283373 to 898156174
Reader-2 read data at 0 = -1445738431
Writer-2 updated value at 1 from 898156174 to -1329349083
Writer-3 updated value at 2 from -905862265 to 451514891
Writer-1 updated value at 2 from 451514891 to -448492006
Writer-3 updated value at 1 from -1329349083 to 1541038582
Writer-1 updated value at 0 from -1445738431 to 1088296261
Reader-7 read data at 0 = 1088296261
Writer-2 updated value at 1 from 1541038582 to 1671438834
Writer-3 updated value at 2 from -448492006 to 903629731
Reader-9 read data at 4 = 1902552094
Reader-1 read data at 3 = 117416559
Writer-2 updated value at 0 from 1088296261 to -1510622655
Writer-3 updated value at 4 from 1902552094 to 1938973751
Writer-1 updated value at 1 from 1671438834 to 990081824
Writer-3 updated value at 2 from 903629731 to -1886415300
Reader-4 read data at 0 = -1510622655
Writer-1 updated value at 1 from 990081824 to -835832587
Writer-2 updated value at 0 from -1510622655 to -1441323291
Reader-3 read data at 3 = 117416559
Reader-8 read data at 4 = 1938973751
Reader-5 read data at 0 = -1441323291
Reader-2 read data at 4 = 1938973751
Writer-2 updated value at 1 from -835832587 to 1320965590
Writer-3 updated value at 0 from -1441323291 to 1450193081
Writer-3 updated value at 0 from 1450193081 to 1571685257
Writer-2 updated value at 3 from 117416559 to 32929555
Writer-1 updated value at 4 from 1938973751 to -1282724285
Reader-6 read data at 0 = 1571685257
Writer-2 updated value at 1 from 1320965590 to -1016930909
Writer-3 updated value at 1 from -1016930909 to -1664254103
Writer-1 updated value at 4 from -1282724285 to 1624191821
Reader-9 read data at 2 = -1886415300
Reader-4 read data at 0 = 1571685257
Reader-7 read data at 3 = 32929555
Writer-2 updated value at 1 from -1664254103 to -268145446
Reader-1 read data at 2 = -1886415300
Writer-3 updated value at 2 from -1886415300 to 157916264
Writer-2 updated value at 2 from 157916264 to -54236949
Writer-1 updated value at 3 from 32929555 to 2022501939
Reader-5 read data at 0 = 1571685257
Writer-3 updated value at 1 from -268145446 to 949760664
Writer-1 updated value at 3 from 2022501939 to -1770978358
Writer-2 updated value at 0 from 1571685257 to -1275842226
Reader-3 read data at 4 = 1624191821
Writer-3 updated value at 4 from 1624191821 to -283078588
Writer-2 updated value at 3 from -1770978358 to 1617243402
Reader-2 read data at 3 = 1617243402
Writer-3 updated value at 3 from 1617243402 to -427125821
Writer-1 updated value at 0 from -1275842226 to -1330520185
Writer-2 updated value at 2 from -54236949 to -1418874655
Reader-8 read data at 0 = -1330520185
Writer-3 updated value at 1 from 949760664 to 421375136
Reader-9 read data at 3 = -427125821
Reader-1 read data at 0 = -1330520185
Writer-1 updated value at 2 from -1418874655 to -1941674894
Writer-2 updated value at 4 from -283078588 to -1872419478
Reader-6 read data at 3 = -427125821
Reader-4 read data at 4 = -1872419478
Writer-3 updated value at 1 from 421375136 to -1925779213
Writer-2 updated value at 4 from -1872419478 to -1748721939
Reader-2 read data at 0 = -1330520185
Writer-1 updated value at 0 from -1330520185 to 1855654581
Reader-7 read data at 1 = -1925779213
Writer-3 updated value at 3 from -427125821 to 1384526673
Writer-1 updated value at 0 from 1855654581 to -331298426
Writer-2 updated value at 0 from -331298426 to -201255097
Reader-3 read data at 4 = -1748721939
Reader-5 read data at 3 = 1384526673
Writer-1 updated value at 0 from -201255097 to 716502752
Writer-2 updated value at 4 from -1748721939 to -1213881163
Writer-3 updated value at 2 from -1941674894 to 203619911
Reader-6 read data at 4 = -1213881163
Writer-3 updated value at 4 from -1213881163 to -220502863
Writer-2 updated value at 1 from -1925779213 to 1847441202
Writer-1 updated value at 3 from 1384526673 to 132391581
Reader-1 read data at 1 = 1847441202
Reader-9 read data at 4 = -220502863
Reader-4 read data at 1 = 1847441202
Writer-3 updated value at 1 from 1847441202 to 817760567
Reader-8 read data at 1 = 817760567
Writer-2 updated value at 3 from 132391581 to 115749354
Reader-3 read data at 2 = 203619911
Writer-1 updated value at 1 from 817760567 to -360265817
Reader-2 read data at 4 = -220502863
Writer-3 updated value at 3 from 115749354 to 534131415
Writer-1 updated value at 1 from -360265817 to -1053794850
Writer-2 updated value at 2 from 203619911 to 1831196640
Writer-3 updated value at 1 from -1053794850 to 364314522
Shutting down Reader-1
Shutting down Reader-3
Shutting down Reader-5
Shutting down Reader-2
Shutting down Reader-4
Shutting down Reader-6
Shutting down Reader-8
Shutting down Reader-7
Shutting down Reader-9
Shutting down Writer-1
Shutting down Writer-2
Shutting down Writer-3
All operations stopped successfully. Final state: [716502752, 364314522, 1831196640, 534131415, -220502863]
```