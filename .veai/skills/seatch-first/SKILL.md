---
name: search-first
description: Research-before-coding workflow. Search for existing tools, libraries, and patterns before writing custom code. Invokes the researcher agent.
used-by:
  - "Code"
  - "General"
schemaVersion: "v0.1"
---

# /search-first — Research Before You Code

Systematizes the "search for existing solutions before implementing" workflow.

## Trigger

Use this skill when:
- Starting a new feature that likely has existing solutions
- Adding a dependency or integration
- The user asks "add X functionality" and you're about to write code
- Before creating a new utility, helper, or abstraction
- Before writing a Vulkan wrapper or LWJGL helper

## Workflow

```
┌─────────────────────────────────────────────┐
│  0. TOOL AVAILABILITY PREFLIGHT             │
│     Check search channels before relying on │
│     them; report skipped channels honestly   │
├─────────────────────────────────────────────┤
│  1. NEED ANALYSIS                           │
│     Define what functionality is needed      │
│     Identify language/framework constraints  │
├─────────────────────────────────────────────┤
│  2. PARALLEL SEARCH (researcher agent)      │
│     ┌──────────┐ ┌──────────┐ ┌──────────┐  │
│     │ Maven /  │ │ MCP /    │ │ GitHub / │  │
│     │ Gradle   │ │ Skills   │ │ Web      │  │
│     └──────────┘ └──────────┘ └──────────┘  │
│     ┌──────────┐ ┌──────────┐               │
│     │ LWJGL    │ │ Project  │               │
│     │ Sources  │ │ Sources  │               │
│     └──────────┘ └──────────┘               │
├─────────────────────────────────────────────┤
│  3. EVALUATE                                │
│     Score candidates (functionality, maint, │
│     community, docs, license, deps)         │
├─────────────────────────────────────────────┤
│  4. DECIDE                                  │
│     ┌─────────┐  ┌──────────┐  ┌─────────┐  │
│     │  Adopt  │  │  Extend  │  │  Build   │  │
│     │ as-is   │  │  /Wrap   │  │  Custom  │  │
│     └─────────┘  └──────────┘  └─────────┘  │
├─────────────────────────────────────────────┤
│  5. IMPLEMENT                               │
│     Add Gradle dependency / Configure MCP /  │
│     Write minimal custom code               │
└─────────────────────────────────────────────┘
```

## Decision Matrix

| Signal | Action |
|--------|--------|
| Exact match, well-maintained, MIT/Apache | **Adopt** — add Gradle dependency and use directly |
| Partial match, good foundation | **Extend** — add dependency + write thin wrapper |
| Multiple weak matches | **Compose** — combine 2-3 small libraries |
| Nothing suitable found | **Build** — write custom, but informed by research |
| LWJGL already provides it | **Adopt** — use LWJGL directly, no extra dependency |
| Same pattern exists in project | **Reuse** — follow existing wrapper pattern from `kotlin/` |

## How to Use

### Step 0: Tool Availability Preflight

This is agent guidance, not an executable setup script. Check only the channels
that are relevant to the task and project in front of you.

| Channel | Check | If missing |
|---------|-------|------------|
| Repository search | `rg --files` and targeted `rg` queries | State that only visible files were inspected |
| Gradle dependencies | `./gradlew dependencies --configuration runtimeClasspath` | Fall back to reading `build.gradle.kts` manually |
| Maven Central | `developer_platform_search` with Wikipedia/StackOverflow | Use official docs/web search |
| GitHub CLI | `gh auth status` | Use public web or local git history only |
| MCP/docs tools | Available tool list or local MCP config | Fall back to official docs/web search |
| Skills directory | `ls ~/.veai/skills` where applicable | Say no local skill catalog was available |
| LWJGL sources | `findSymbolSource` / attached source JARs in IDE | Use LWJGL website or GitHub mirror |

### Quick Mode (inline)

Before writing a utility or adding functionality, run through:

0. Does this already exist in the repo? → `rg` through `kotlin/` and `tutor/` first
1. Does LWJGL already provide this? → Check `org.lwjgl.vulkan`, `org.lwjgl.glfw`, attached source JARs
2. Is there a Gradle dependency for it? → `./gradlew dependencies` + search Maven Central
3. Is there an MCP for this? → Check available MCP servers
4. Is there a skill for this? → Check `~/.veai/skills/`
5. Is there a GitHub implementation/template? → Run GitHub search for maintained OSS before writing net-new code

### Full Mode (agent)

For non-trivial functionality, launch the researcher agent:

```
Agent(subagent_type="general-purpose", prompt="
  Research existing tools for: [DESCRIPTION]
  Language/framework: Kotlin + LWJGL + Vulkan
  Constraints: [ANY]

  Search in order:
  1. Project sources (kotlin/, tutor/) — reuse existing wrappers
  2. LWJGL sources — check if the API already exists
  3. Gradle dependencies — ./gradlew dependencies
  4. Maven Central — search for Kotlin/Vulkan/LWJGL libraries
  5. MCP servers, Veai skills
  6. GitHub — maintained Kotlin/Vulkan OSS projects

  Return: Structured comparison with recommendation
")
```

## Kotlin + Gradle Specific Search

### 1. Project Sources First

```kotlin
// Check existing wrapper patterns
src/main/kotlin/me/vektory79/vulkan/kotlin/
├── struct/          # KVkXxx wrapper classes — follow this pattern
├── flags/           # KVkXxx enum/KVkBits — follow this pattern
└── *.kt             # Core classes (KVkInstance, KVkDevice, etc.)
```

**Before writing a new wrapper**, check:
- Does the `struct/` directory already have a similar wrapper?
- Does the `flags/` directory already define the needed flag bits?
- Does `VkStructs.kt` or `VkFunctions.kt` provide the needed utility?

### 2. LWJGL Source Search

LWJGL is the primary dependency. Before writing custom JNI/native code:

| What to check | Where |
|---------------|-------|
| Vulkan structures | `org.lwjgl.vulkan` package — `VkXxx` classes |
| GLFW windowing | `org.lwjgl.glfw` package |
| STB image loading | `org.lwjgl.stb` package |
| Vulkan Memory Allocator | `org.lwjgl.vma` package |
| Shader compilation | `org.lwjgl.shaderc` package |

**How to search LWJGL sources:**
- Use IDE's `findSymbolSource` to locate LWJGL class definitions
- Check attached source JARs from Gradle (`sources` configuration in `build.gradle.kts`)
- Search LWJGL GitHub: `https://github.com/LWJGL/lwjgl3`
- Check LWJGL customizer: `https://www.lwjgl.org/customize`

### 3. Gradle Dependency Search

```bash
# List all runtime dependencies with transitive deps
./gradlew dependencies --configuration runtimeClasspath

# List compile-time dependencies
./gradlew dependencies --configuration compileClasspath

# Check for specific artifact
./gradlew dependencies | grep -i "vulkan\|glfw\|stb"
```

**Maven Central search strategies:**
- Use `developer_platform_search` with query like `"kotlin vulkan wrapper [kotlin]"`
- Search StackOverflow for `"kotlin lwjgl vulkan"` patterns
- Check GitHub for `stars:>100 language:kotlin vulkan` repositories

### 4. Kotlin Ecosystem

| Need | Search For |
|------|------------|
| Coroutines / async | `kotlinx-coroutines-core` |
| Serialization | `kotlinx-serialization-json` |
| CLI parsing | `kotlin-argparser`, `clikt` |
| Logging | `kotlin-logging`, `slf4j` |
| Testing | `kotlin-test`, `kotest`, `mockk` |
| DI | `koin`, `inject`, `dagger` |

## Search Shortcuts by Category

### Vulkan / 3D Development
- Vulkan wrappers (Kotlin) → Check project's `kotlin/` first, then GitHub
- LWJGL modules → Already in `build.gradle.kts` (lwjgl-vulkan, lwjgl-glfw, etc.)
- Shader compilation → `org.lwjgl.shaderc` (already a dependency)
- Texture loading → `org.lwjgl.stb` (already a dependency)
- Memory allocation → `org.lwjgl.vma` (already a dependency)

### Kotlin Development
- Linting → `detekt` (Gradle plugin)
- Formatting → `ktlint` (Gradle plugin)
- Testing → `kotlin-test`, `kotest`
- Benchmarking → `kotlin-benchmark` (Gradle plugin)

### Data & APIs
- HTTP clients → `khttp`, `ktor-client`
- Validation → `validatorfr`, `kotlinx-serialization`
- Database → Exposed (Kotlin SQL), Exposed-Dao

### Build & CI
- Multi-platform → `kotlin("multiplatform")` plugin
- Native compilation → `kotlin("native")` plugin
- Publishing → `maven-publish`, `shadow` (fat JAR)

## Integration Points

### With planner agent
The planner should invoke researcher before Phase 1 (Architecture Review):
- Researcher identifies available tools
- Planner incorporates them into the implementation plan
- Avoids "reinventing the wheel" in the plan

### With architect agent
The architect should consult researcher for:
- Technology stack decisions
- Integration pattern discovery
- Existing reference architectures
- LWJGL module selection (already bundled vs. needs addition)

### With iterative-retrieval skill
Combine for progressive discovery:
- Cycle 1: Broad search (project sources, LWJGL, Maven Central)
- Cycle 2: Evaluate top candidates in detail
- Cycle 3: Test compatibility with project constraints

## Examples

### Example 1: "Add texture loading"
```
Need: Load PNG/DDS textures for Vulkan images
Search 1: Project sources — no texture loader in kotlin/
Search 2: LWJGL sources — org.lwjgl.stb has StbImage (stbi_load)
Search 3: Gradle deps — lwjgl-stb already in build.gradle.kts
Action: ADOPT — use StbImage from existing lwjgl-stb dependency
Result: Zero new dependencies, wrap StbImage in Kotlin helper
```

### Example 2: "Add shader compilation"
```
Need: Compile GLSL shaders to SPIR-V at runtime
Search 1: Project sources — no shader compiler in kotlin/
Search 2: LWJGL sources — org.lwjgl.shaderc has Shaderc
Search 3: Gradle deps — lwjgl-shaderc already in build.gradle.kts
Action: ADOPT — use Shaderc from existing lwjgl-shaderc dependency
Result: Zero new dependencies, wrap Shaderc in Kotlin helper
```

### Example 3: "Add Vulkan memory allocator"
```
Need: Efficient GPU memory management
Search 1: LWJGL sources — org.lwjgl.vma has Vma (AMD Vulkan Memory Allocator)
Search 2: Gradle deps — lwjgl-vma already in build.gradle.kts
Action: ADOPT — use Vma from existing lwjgl-vma dependency
Result: Zero new dependencies, create KVkAllocator wrapper
```

### Example 4: "Add logging"
```
Need: Structured logging for Vulkan debug output
Search 1: Maven Central — "kotlin logging" → kotlin-logging
Search 2: Gradle deps — no logging library present
Action: ADOPT — add io.github.microutils:kotlin-logging dependency
Result: 1 new dependency, standard Kotlin logging pattern
```

## Anti-Patterns

- **Jumping to code**: Writing a utility without checking if LWJGL or the project already has it
- **Ignoring LWJGL**: Not checking if the needed Vulkan/GLFW API is already exposed by LWJGL
- **Ignoring existing wrappers**: Not checking `kotlin/struct/` and `kotlin/flags/` before writing new wrappers
- **Silent skipping**: Reporting "nothing found" when a search channel was unavailable
- **Over-customizing**: Wrapping a library so heavily it loses its benefits
- **Dependency bloat**: Installing a massive package for one small feature
- **Ignoring Gradle BOM**: Adding versioned dependencies when `lwjgl-bom` already manages versions
- **Skipping native classifier**: Adding LWJGL dependency without the matching `runtimeOnly` native classifier

## Verification Checklist

Before declaring research complete:

- [ ] Searched project sources (`kotlin/`, `tutor/`) for existing implementations
- [ ] Checked LWJGL source JARs for the needed API
- [ ] Ran `./gradlew dependencies` to verify current dependency tree
- [ ] Searched Maven Central / GitHub for Kotlin-compatible alternatives
- [ ] Documented why "build custom" was chosen (if applicable)
- [ ] Noted any new Gradle dependencies with their exact coordinates