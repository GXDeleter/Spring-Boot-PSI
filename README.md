# Spring-Boot-PSI
用Spring Boot 開發簡易進銷存微服務系統

## 主要內容

1. **PSI 資料表關聯圖：** 依資料表關係圖，建立資料庫表格
2. **部門資料：** 新增、修改或刪除部門資料
3. **員工資料：** 新增、修改或刪除員工資料
4. **客戶資料：** 新增、修改或刪除客戶資料，連結該客戶下單資料
5. **訂單資料：** 新增、修改或刪除訂單資料，連結該訂單明細
6. **商品資料：** 新增、修改或刪除商品資料
7. **供應商資料：** 新增、修改或刪除供應商資料
8. **採購單資料：** 新增、修改或刪除採購單資料，連結該採購單明細
9. **庫存資料：** 統計商品進、出貨總量，計算目前庫存量

## Demo

1. **PSI 資料表關聯圖：依資料表關係圖，建立資料庫表格**

    ![psi_table](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/src/main/resources/static/images/psi_tables.png?raw=true)

2. **部門資料** 

    2.1 部門頁面
    ![department](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/department.PNG)
    
    2.2 操作：新增部門
    ![departmentGIF](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/GIF/Department.gif)
    
3. **員工資料** 

    3.1 員工頁面
    ![employee](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/employee.PNG?raw=true)

    3.2 新增員工資料
    ![EmployeeGIF](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/GIF/Employee.gif)

    3.3 部門員工資料查詢
    ![Employee2GIF](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/GIF/Employee-2.gif)

4. **客戶資料** 

    4.1 客戶資料頁面
    ![customer](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/customer.PNG)

5. **訂單資料** 

    5.1 訂單資料頁面
     ![order](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/order.PNG)

    5.2 新增客戶訂單
    ![OrderGIF](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/GIF/Order.gif)
    
    5.3 新增訂單內容
    ![OrderItemGIF](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/GIF/OrderItem.gif)

    5.4 若訂購商品無庫存或超過庫存數量，則無法新增，且顯示錯誤內容
    ![OrderItem-update-alon](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/OrderItem-update-alon.png)

6. **商品資料** 

    6.1 商品資料頁面
    ![product](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/product.PNG)

7. **供應商資料** 

    7.1 供應商資料頁面
    ![supplier](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/supplier.PNG)

8. **採購單資料**

    8.1 採購單資料頁面
    ![purchase](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/purchase.PNG)

    8.2 新增採購單資料
    ![purchaseGIF](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/GIF/purchase.gif)

9. **庫存資料**

    9.1 庫存資料頁面
    ![inventory](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/%E5%BA%AB%E5%AD%98%E8%B3%87%E6%96%99.PNG)

    9.2 進貨的採購單資料與校受的訂單資料查詢
    ![inventoryGIF](https://github.com/GXDeleter/Spring-Boot-PSI/blob/main/Demo/GIF/inventory.gif)

