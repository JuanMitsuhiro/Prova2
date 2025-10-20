CREATE DATABASE av2LabBD
GO
USE av2LabBD
GO
INSERT INTO Curso (nome) VALUES 
('Análise e Desenvolvimento de Sistemas (ADS)'),
('Comércio Exterior'),
('Desenvolvimento de Produtos Plásticos'),
('Desenvolvimento de Software Multiplataforma'),
('Gestão de Recursos Humanos'),
('Gestão Empresarial'),
('Logística'),
('Polímeros')
GO
CREATE TABLE temp_curiosidades(
id				INT		IDENTITY(1,1),
codigo			INT,
desenvolvedora	INT,
texto			VARCHAR(255)
PRIMARY KEY(id)
)
GO
CREATE PROCEDURE sp_verificalimite
AS
IF (SELECT COUNT(*) FROM temp_curiosidades) > 3
BEGIN
	DELETE FROM temp_curiosidades WHERE id NOT IN(
		SELECT TOP 3 id
		FROM temp_curiosidades
		ORDER BY id DESC
	)
END
GO
CREATE PROCEDURE [dbo].[sp_validalogin] (@login VARCHAR(15), @senha VARCHAR(15), @valida BIT OUTPUT)
AS
IF (@login = 'admin' AND @senha = 'RAj@lfO%')
BEGIN
	SET @valida = 1
END
ELSE
BEGIN
	SET @valida = 0
END
GO
CREATE TRIGGER t_updelcandidato ON candidato
FOR UPDATE, DELETE
AS
BEGIN
	RAISERROR('Não é permitido atualizar ou excluir candidatos', 16, 1)
	ROLLBACK TRANSACTION
END
GO
CREATE TRIGGER t_delcuriosidade ON curiosidade
FOR DELETE
AS
BEGIN
	RAISERROR('Não é permitido excluir curiosidades', 16, 1)
	ROLLBACK TRANSACTION
END
GO
CREATE PROCEDURE [dbo].[sp_retornacuriosidade](@desenvolvedora INT, @res INT OUTPUT)
AS
BEGIN
	DECLARE @qtde INT = (SELECT COUNT(*) FROM dbo.f_escolheCuriosidade(@desenvolvedora)),
			@codigo INT = 0,
			@indice INT

	SET @indice = CAST(RAND() * @qtde + 1 AS INT)
	SET @codigo = (SELECT codigo FROM 
		(
			SELECT codigo, ROW_NUMBER() OVER (ORDER BY codigo) as linha 
			FROM dbo.f_escolheCuriosidade(@desenvolvedora)
		) AS codCapt
		WHERE linha = @indice
	)
	IF NOT EXISTS (SELECT id FROM temp_curiosidades WHERE codigo = @codigo)
	BEGIN 
		INSERT INTO temp_curiosidades (codigo, desenvolvedora, texto)
		SELECT codigo, desenvolvedora_codigo, texto FROM curiosidade WHERE codigo = @codigo
		EXEC sp_verificalimite
	END
	SET @res = @codigo
END
GO
CREATE FUNCTION [dbo].[f_escolheCuriosidade](@desenvolvedora INT)
RETURNS @tabela TABLE (
codigo			INT,
desenvolvedora	INT,
texto			VARCHAR(255)
)
AS
BEGIN
	INSERT INTO @tabela 
		SELECT codigo, desenvolvedora_codigo, texto FROM curiosidade WHERE desenvolvedora_codigo = @desenvolvedora ORDER BY codigo

RETURN
END
