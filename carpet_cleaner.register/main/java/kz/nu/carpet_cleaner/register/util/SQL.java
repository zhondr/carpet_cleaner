package kz.nu.carpet_cleaner.register.util;

public class SQL extends AbstractSQL<SQL> {

  @Override
  public SQL getSelf() {
    return this;
  }

  @Override
  protected SQL createNew() {
    return new SQL();
  }

} 
