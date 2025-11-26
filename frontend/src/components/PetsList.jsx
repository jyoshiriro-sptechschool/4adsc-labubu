import { useState, useEffect } from 'react';
import axios from 'axios';
import './PetsList.css';

function PetsList() {
  const [pets, setPets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');

  const fetchPets = async (pesquisa = '') => {
    try {
      setPets([]);
      setLoading(true);
      const url = pesquisa 
        ? `/api/pets?pesquisa=${encodeURIComponent(pesquisa)}`
        : '/api/pets';
      const response = await axios.get(url);
      console.log('Pets recebidos: ', response.data);
      
      setPets(Array.isArray(response.data) ? response.data : []);
      setError(null);
    } catch (err) {
      setError('Erro ao carregar os pets: ' + err.message);
      console.error('Erro ao buscar pets:', err);
      setPets([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPets();
  }, []);

  const handleSearch = () => {
    fetchPets(searchTerm);
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter' && searchTerm.length >= 3) {
      handleSearch();
    }
  };

  return (
    <div className="pets-container">
      <h1>Pets Care farmador de aura</h1>
      
      <div className="search-box">
        <input
          id="in-pesquisa"
          type="text"
          placeholder="Pesquisar pets..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          onKeyPress={handleKeyPress}
          className="search-input"
        />
        <button 
          onClick={handleSearch} 
          className="search-button"
          disabled={searchTerm.length < 3}
        >
          Pesquisar
        </button>
        <button 
          onClick={() => {
            setSearchTerm('');
            fetchPets();
          }} 
          className="list-all-button"
        >
          Listar Todos
        </button>
      </div>

        {loading && (
          <div className="loading">Pesquisando pets...</div>
        )
        }

        {error && (
          <div className="error">{error}</div>
        )
        }

      {(!loading && pets.length > 0) && (
        <div id="resultado-pets" className="pets-count">
          {pets.length} {pets.length === 1 ? 'pet encontrado' : 'pets encontrados'}
        </div>
      )}

      <div className="pets-grid">
        {pets.map((pet) => (
          <div id="pet-card" key={pet.id} className={`pet-card ${!pet.ativo ? 'inactive' : ''}`}>
            <div className="pet-header">
              <h2>{pet.nomePet}</h2>
              {!pet.ativo && <span className="badge-inactive">Inativo</span>}
            </div>
            
            <div className="pet-info">
              <div className="info-section">
                <h3>Informações do Pet</h3>
                <p><strong>ID:</strong> {pet.id}</p>
                <p><strong>Nome:</strong> {pet.nomePet}</p>
                <p><strong>Espécie:</strong> {pet.especie.nome} ({pet.especie.codigo})</p>
                <p><strong>Raça:</strong> {pet.raca.nome} ({pet.raca.codigo})</p>
                <p><strong>Peso:</strong> {pet.peso} kg</p>
                <p><strong>Altura:</strong> {pet.altura} m</p>
                <p><strong>Nascimento:</strong> {new Date(pet.nascimento).toLocaleDateString('pt-BR')}</p>
                <p><strong>Validade do Chip:</strong> {new Date(pet.validadeChip).toLocaleDateString('pt-BR')}</p>
              </div>

              <div className="info-section">
                <h3>Informações do Dono</h3>
                <p><strong>Nome:</strong> {pet.nomeDono}</p>
                <p><strong>CPF:</strong> {pet.cpfDono}</p>
                <p><strong>Email:</strong> {pet.emailDono}</p>
                <p><strong>Telefone:</strong> {pet.telefoneDono}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
      
      {(!loading && pets.length === 0) && (
        <p id="resultado-pets" className="no-pets">Nenhum pet encontrado.</p>
      )}
    </div>
  );
}

export default PetsList;
