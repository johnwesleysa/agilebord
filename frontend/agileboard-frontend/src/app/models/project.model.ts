
export interface Project {
  id: number;
  title: string;
  description: string;

}

export interface Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  number: number;
  size: number;
}


