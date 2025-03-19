# 🖌️ OSGi Framework - Paint Shop System

## 📌 Project Overview
This project is an **OSGi-based modular paint shop management system**. It allows customers to **browse and purchase paints**, while administrators manage **paint inventory and delivery users**. The system follows a **publisher-subscriber architecture** with different OSGi bundles.

## 🔧 Features
### **Customer Module**
✔ Sign up & Log in  
✔ Browse available paints  
✔ Place orders  
✔ Choose delivery options  

### **Admin Module**
✔ Add new paint items  
✔ Manage delivery users  

### **Delivery Module**
✔ View pending delivery orders  

### **Technology Stack**
- **Java** (OSGi framework)
- **Equinox OSGi Runtime**
- **Eclipse IDE**
- **OSGi Bundles** (Producers & Consumers)

---

## ⚙️ **OSGi Bundles & Architecture**
### **Producers (Publishers)**
- `Admin Producer`
- `Customer Producer`
- `Delivery Producer`
- `Mixer Producer`
- `Order Producer`

### **Consumers (Subscribers)**
- `Admin Consumer`
- `Customer Consumer`
- `Delivery Consumer`

Each producer **publishes services** that are consumed by the respective consumers.

---

## 🚀 **Installation & Setup**
### **1️⃣ Running OSGi Bundles from Eclipse**
#### **Step 1: Open "Run Configurations"**
1. Go to **Run** → **Run Configurations**.
2. Select **OSGi Framework**.
3. Choose the project **Paint Shop**.
4. Select all required **Producer & Consumer Bundles**.
5. Set **Auto-Start** to `true` for all bundles.
6. Click **Apply** → **Run**.

#### **Step 2: Verify Running Bundles**
1. Open the **OSGi console** in Eclipse.
2. Type:
   ```sh
   ss
   ```
3. Ensure all bundles are in **ACTIVE** state.

---

### **2️⃣ Running Exported JAR Bundles**
#### **Step 1: Navigate to JAR Directory**
```sh
cd /path/to/your/JARs
```

#### **Step 2: Start OSGi Framework**
```sh
java -jar org.eclipse.osgi_VERSION.jar -console
```
_(Replace `VERSION` with your Equinox OSGi version)_

#### **Step 3: Install Bundles**
```sh
install file:Admin\ Consumer.jar
install file:Admin\ Producer.jar
install file:Customer\ Consumer.jar
install file:Customer\ Producer.jar
install file:Delivery\ Consumer.jar
install file:Delivery\ Producer.jar
install file:Mixer\ Producer.jar
install file:Order\ Producer.jar
```

#### **Step 4: Start All Bundles**
```sh
start [BUNDLE_ID]
```
_(Replace `[BUNDLE_ID]` with the actual ID from `ss` output)_

#### **Step 5: Stop & Uninstall (If Needed)**
```sh
stop [BUNDLE_ID]
uninstall [BUNDLE_ID]
```

---

## 📖 **System Workflows**
### **Customer Workflow**
1. **Login/Register**
2. **View available paints**
3. **Select paint & place order**
4. **Choose delivery option**
5. **Order processed**

### **Admin Workflow**
1. **Login**
2. **Add new paints**
3. **Manage delivery users**

### **Delivery Workflow**
1. **Login**
2. **View pending orders**
3. **Complete delivery**

---

## 🖥️ **Manifest Implementation**
- Each bundle has its own **MANIFEST.MF** file defining:
  - **Imported & Exported Services**
  - **Bundle Dependencies**
  - **Service Registrations**

---

## 📷 **Screenshots**
✔ System architecture diagram  
✔ OSGi bundle relationships  
✔ Running configurations in Eclipse  

---

## 🔗 **GitHub Repository**
[GitHub Repository](https://github.com/amila1998/OSGI_FrameWork-Paint-Shop)

---

## 📌 **Contributors**
- **Senarathne S M A D**  
  _Sri Lanka Institute of Information Technology (SLIIT)_

---
🚀 **Now you can deploy, run, and extend this OSGi-based Paint Shop System!**  
