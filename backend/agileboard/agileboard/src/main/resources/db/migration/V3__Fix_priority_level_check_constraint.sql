-- Primeiro, remove a regra antiga que estava com o erro de digitação.
-- O 'IF EXISTS' garante que o script não falhe se a constraint não existir.
ALTER TABLE tasks DROP CONSTRAINT IF EXISTS tasks_priority_level_check;

-- Depois, adiciona a nova regra com os valores corretos.
ALTER TABLE tasks ADD CONSTRAINT tasks_priority_level_check
CHECK (priority_level IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL'));