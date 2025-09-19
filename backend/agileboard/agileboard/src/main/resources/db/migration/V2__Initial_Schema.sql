-- Adiciona a nova coluna 'status' à tabela de tasks.
ALTER TABLE tasks ADD COLUMN status VARCHAR(255);

-- Define um valor padrão ('A_FAZER') para todas as tasks que já existem e não têm um status.
UPDATE tasks SET status = 'A_FAZER' WHERE status IS NULL;

-- Torna a coluna 'status' obrigatória (NOT NULL) após garantir que todas as linhas têm um valor.
ALTER TABLE tasks ALTER COLUMN status SET NOT NULL;