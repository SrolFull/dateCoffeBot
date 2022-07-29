package bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bot.repository.DateCoffeeRepository;

@Service
public class UserDBService {
  @Autowired
  DateCoffeeRepository repository;
}
