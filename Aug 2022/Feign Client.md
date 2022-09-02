# Feign Client
```java
@FeignClient(name = "TODO-MANAGEMENT", path = "/api/todos")
public interface TodoFeignClient {

	@GetMapping("/{id}")
	public ResponseEntity<TodoDTO> getTodo(@PathVariable final Long id);

	@GetMapping
	public ResponseEntity<List<TodoDTO>> getAllTodos();

	@PostMapping
	public ResponseEntity<Long> createTodo(@RequestBody final TodoDTO todoDTO);

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable final Long id);

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateTodo(@PathVariable final Long id, @RequestBody final TodoDTO todoDTO);
}
```

# Actual Resource
```java
@RestController
@RequestMapping(value = "/api/todos", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoResource {

    private final TodoService todoService;

    public TodoResource(final TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable final Long id) {
        return ResponseEntity.ok(todoService.get(id));
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Todo>> getTodoByUserId(@PathVariable final Long id) {
        return ResponseEntity.ok(todoService.findByUserId(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTodo(@RequestBody @Valid final TodoDTO todoDTO) {
        return new ResponseEntity<>(todoService.create(todoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable final Long id,
            @RequestBody @Valid final TodoDTO todoDTO) {
        todoService.update(id, todoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTodo(@PathVariable final Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```