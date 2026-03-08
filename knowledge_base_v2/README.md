# V2 知识库 - ModernUI 框架深度分析

欢迎来到 `ModernUI` 框架的 V2 版知识库。本文档是在对 V1 版进行二次审计，并结合对 `TestFragment.java` 和 `ModernUI.java` 的深度分析后，形成的**最终版**架构认知。它代表了我们对该项目当前最深入、最准确的理解。

## V2 版架构认知升级

`V2` 在 `V1` 提出的“引擎->领域->服务”三层架构模型的基础上，融入了对框架引导流程和核心机制更精确的洞察，主要升级点如下：

1.  **引擎层升级：揭示双线程模型**
    -   `V1` 将引擎简化为单线程模型。`V2` 修正了这一点，明确了框架由一个 **UI 线程 (`Main-Thread`)** 和一个 **渲染线程 (`Render-Thread`)** 构成，并通过 `Handler` 和锁机制在 `ViewRoot` 中进行同步。这揭示了框架性能优化的核心设计。

2.  **领域层扩展：纳入 `Activity/Fragment` 架构**
    -   `V1` 将领域核心局限于 `View` 系统。`V2` 将 `Activity` 和 `Fragment` 纳入核心领域层，明确了 `Activity` 是 `Context` 的提供者和顶层生命周期管理者，而 `Fragment` 是官方推荐的、更高阶的 UI 组织与控制器。

3.  **启动流程解密：定位引导程序 `ModernUI.java`**
    -   `V1` 对项目如何启动是模糊的。`V2` 通过分析 `TestFragment.java`，成功定位到引导程序 `ModernUI.java`，并完整地梳理了从 `main()` 函数到 `View` 树上屏的完整引导链路。

---

## V2 知识库导航

以下是 V2 版的全新文档结构，它更精确地反映了项目的真实运作模式。

### 📄 [01 - 引擎驱动层 (The Engine)](./01_引擎驱动层.md)

本篇文档聚焦于整个框架的“心脏”，深入剖析了其独特的双线程驱动模型。

-   **双线程模型**: UI 线程 vs 渲染线程。
-   **线程同步**: `ViewRootImpl.endDrawLocked()` 中 `wait/notify` 的关键作用。
-   **`Choreographer`**: UI 线程的“节拍器”。

### 📄 [02 - 核心领域层 (The Core Domain)](./02_核心领域层.md)

本篇文档将领域核心扩展为 `View` 系统和 `Fragment` 系统的总和。

-   **`Activity`/`Fragment`**: `Activity` 作为 `Context` 提供者，`Fragment` 作为 UI 控制器的角色和生命周期。
-   **`View`/`ViewGroup`**: 底层 UI 原子和容器的三大流程与事件分发。
-   **工作流**: 详细描述了从 `ModernUI.run()` 到 `Fragment` 创建 `View` 的完整流程。

### 📄 [03 - 服务表现层 (The Service & Presentation)](./03_服务表现层.md)

本篇文档归纳了所有为“核心领域层”提供支持的被动服务模块。

-   **`graphics`**: 作为“渲染服务”，提供 `Canvas` API。
-   **`resources`**: 作为“资源服务”，由 `ModernUI` 实例创建并持有。
-   **`widget`**: 作为 `View` 和 `ViewGroup` 的“预制件集合”。
-   **`util`**: 作为独立的“通用基础设施”。

### 📄 [04 - 系统暗线与隐式机制](./04_系统暗线与隐式机制.md)

本篇文档是本次深度侦查的最终成果，揭示了隐藏在显式代码之下的关键“暗线”。

-   **引导协议**: `ModernUI.java` 是如何通过 `run()` 方法一步步初始化 `Looper`、`Window`、`ViewRoot` 和 `Fragment` 的。
-   **`Context` 实现**: `ModernUI` 实例自身就是根 `Context`，并通过依赖注入传递给 `Fragment`。
-   **隐式加载**: 框架依赖 `LWJGL` 内置的机制来隐式加载图形后端。
-   **全局异常处理**: 依赖 `Looper.loop()` 的 `try-catch` 实现“线程级”的异常兜底。
