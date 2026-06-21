---
filePattern: "**/*"
---

---
filePattern: "**/*"
---

# escalation

Stop and ask a human before proceeding if:

- Requirements are ambiguous or conflict between rules, support files, Notion, or the prompt.
- You cannot complete the requested task with the available context, permissions, tools, or project state.
- Continuing requires inventing missing requirements, using an unverified workaround, or silently skipping part of the request.
- You are about to make an architectural decision not explicitly covered by supporting docs, Notion, the prompt, or an existing cursor rule (e.g. new package, new dependency, new table, new proto message).
- You are unsure which of multiple valid approaches to take.
- A test expectation seems wrong or contradicts the documented behavior.
- An MCP tool or other integration is not available for fetching potentially relevant data.
- A best practice has divergent guidance for greenfield vs brownfield. Confirm which applies before proceeding.

Ask what to do next and offer clear options when possible, such as clarifying requirements, trying an alternative approach, continuing investigation, or skipping the blocked part.

Do not guess or pick a "reasonable default". Ask first. The cost of a wrong autonomous decision is much higher than the cost of a question.

