export interface Task {
  id: number;
  title: string;
  description: string;
  priorityLevel: string; // LOW, MEDIUM, etc.
  status: string;
  sprintId: number;
}

// DTO para atualização, especialmente para o status
export interface TaskUpdateDTO {
  title?: string;
  description?: string;
  priorityLevel?: string;
  status?: string;
}
